package controllers;

import data.ConnectionFactory;
import models.Funcionario;
import models.Reserva;

import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private Connection connection;
    private HospedeDAO hospedeDAO;
    private QuartoDAO quartoDAO;

    public ReservaDAO() {
        this.connection = ConnectionFactory.getConnection();
        this.hospedeDAO = new HospedeDAO();
        this.quartoDAO = new QuartoDAO();
    }

    public void inserir(Reserva reserva) {
    try {
        if (!hospedeDAO.existePorNome(reserva.getNomeHospede())) {
            System.out.println("Erro: Hóspede não encontrado: " + reserva.getNomeHospede());
            return;
        }

        if (!quartoDAO.existePorNumero(reserva.getNumeroQuarto())) {
            System.out.println("Erro: Quarto não encontrado: " + reserva.getNumeroQuarto());
            return;
        }

        // Calcula quantidade de dias
        long dias = ChronoUnit.DAYS.between(reserva.getDataEntrada(), reserva.getDataSaida());
        if (dias <= 0) {
            System.out.println("Erro: Datas inválidas para reserva.");
            return;
        }

        // Obtém preço do quarto
        double preco = obterPrecoDoQuarto(reserva.getNumeroQuarto());

        // Calcula total
        double valorTotal = preco * dias;
        reserva.setValorTotal(valorTotal);  // **Certifique-se de ter o método setValorTotal em Reserva**

        // Agora pega o id do hóspede pelo nome
        int idHospede = buscarIdHospedePorNome(reserva.getNomeHospede());

        // Pega o id do quarto pelo número
        int idQuarto = buscarIdQuartoPorNumero(reserva.getNumeroQuarto());

        if (!quartoEstaDisponivel(idQuarto)) {
            System.out.println("Erro: O quarto está ocupado e não pode ser reservado.");
            return;
        }

        String sql = "INSERT INTO reservas (id_hospede, id_quarto, data_entrada, data_saida, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idHospede);
            stmt.setInt(2, idQuarto);
            stmt.setDate(3, Date.valueOf(reserva.getDataEntrada()));
            stmt.setDate(4, Date.valueOf(reserva.getDataSaida()));
            stmt.setDouble(5, valorTotal);
            stmt.executeUpdate();

            System.out.println("Reserva cadastrada com sucesso! Valor total: R$ " + valorTotal);
        }

        marcarQuartoComoOcupado(reserva.getNumeroQuarto());

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private double obterPrecoDoQuarto(int numeroQuarto) {
        String sql = "SELECT preco FROM quartos WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numeroQuarto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("preco");
            } else {
                throw new SQLException("Quarto não encontrado: " + numeroQuarto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar preço do quarto: " + e.getMessage());
        }
    }

    private void marcarQuartoComoOcupado(int numeroQuarto) {
        String sql = "UPDATE quartos SET status = 'ocupado' WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numeroQuarto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> listar() {
    List<Reserva> lista = new ArrayList<>();
    String sql = "SELECT r.id, h.nome AS nome_hospede, q.numero AS numero_quarto, " +
                 "r.data_entrada, r.data_saida, r.valor_total " +
                 "FROM reservas r " +
                 "JOIN hospedes h ON r.id_hospede = h.id " +
                 "JOIN quartos q ON r.id_quarto = q.id";

    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Reserva r = new Reserva(
                rs.getInt("id"),
                rs.getString("nome_hospede"),
                rs.getInt("numero_quarto"),
                rs.getDate("data_entrada").toLocalDate(),
                rs.getDate("data_saida").toLocalDate(),
                rs.getDouble("valor_total")
            );
            lista.add(r);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}

    public void finalizarReserva(int id) {
    String selectSql = "SELECT id_quarto FROM reservas WHERE id = ?";
    String deleteSql = "DELETE FROM reservas WHERE id = ?";
    String updateQuartoSql = "UPDATE quartos SET status = 'disponível' WHERE id = ?";

    try (
        PreparedStatement selectStmt = connection.prepareStatement(selectSql);
        PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
        PreparedStatement updateStmt = connection.prepareStatement(updateQuartoSql)
    ) {
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            int idQuarto = rs.getInt("id_quarto");

            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();

            updateStmt.setInt(1, idQuarto);
            updateStmt.executeUpdate();

            System.out.println("Reserva Paga e quarto liberado!");
        } else {
            System.out.println("Reserva não encontrada.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public int buscarIdHospedePorNome(String nomeHospede) throws SQLException {
    String sql = "SELECT id FROM hospedes WHERE nome = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, nomeHospede);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Hóspede não encontrado: " + nomeHospede);
        }
    }
}

public int buscarIdQuartoPorNumero(int numeroQuarto) throws SQLException {
    String sql = "SELECT id FROM quartos WHERE numero = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, numeroQuarto);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Quarto não encontrado: " + numeroQuarto);
        }
    }
}

private boolean quartoEstaDisponivel(int idQuarto) {
    String sql = "SELECT status FROM quartos WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, idQuarto);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String status = rs.getString("status");
            return status.equalsIgnoreCase("disponível");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // assume indisponível em caso de erro
}

    public Funcionario buscarPorLogin(String login) {
        String sql = "SELECT * FROM funcionarios WHERE login = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getString("login"),
                    rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}