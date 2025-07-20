package controllers;

import data.ConnectionFactory;
import Models.Quarto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {
    private Connection connection;

    public QuartoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserir(Quarto quarto) {
        String sql = "INSERT INTO quartos (numero, tipo, status, preco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quarto.getNumero());
            stmt.setString(2, quarto.getTipo());
            stmt.setString(3, "disponível"); // Sempre inicia como disponível
            stmt.setDouble(4, quarto.getPreco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Quarto> listar() {
        List<Quarto> lista = new ArrayList<>();
        String sql = "SELECT * FROM quartos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Quarto q = new Quarto(
                    rs.getInt("id"),
                    rs.getInt("numero"),
                    rs.getString("tipo"),
                    rs.getString("status"),
                    rs.getDouble("preco")
                );
                lista.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM quartos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existePorNumero(int numero) {
    String sql = "SELECT COUNT(*) FROM quartos WHERE numero = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, numero);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}


