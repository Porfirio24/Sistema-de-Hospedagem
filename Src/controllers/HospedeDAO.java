package controllers;

import Models.Hospede;
import data.ConnectionFactory;
import java.sql.*;
import java.util.*;

public class HospedeDAO {
    private Connection connection;

    public HospedeDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserir(Hospede hospede) {
        String sql = "INSERT INTO hospedes (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hospede.getNome());
            stmt.setString(2, hospede.getCpf());
            stmt.setString(3, hospede.getTelefone());
            stmt.setString(4, hospede.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hospede> listar() {
        List<Hospede> lista = new ArrayList<>();
        String sql = "SELECT * FROM hospedes";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Hospede h = new Hospede(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                lista.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM hospedes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hóspede deletado com sucesso.");
            } else {
                System.out.println("Nenhum hóspede encontrado com esse ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existePorNome(String nome) {
    String sql = "SELECT COUNT(*) FROM hospedes WHERE nome = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }
    public Hospede buscarPorCPF(String cpf) {
    String sql = "SELECT * FROM hospedes WHERE cpf = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Hospede(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("email")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public void atualizarCampo(int id, String campo, String novoValor) {
        String sql = "UPDATE hospedes SET " + campo + " = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoValor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Informação atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }
}