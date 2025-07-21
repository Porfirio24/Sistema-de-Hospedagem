package data;

import models.Funcionario;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FuncionarioDAO {
    public List<Funcionario> buscarTodos() throws IOException, ClassNotFoundException {
        return Database.carregarFuncionarios();
    }

    public Optional<Funcionario> buscarPorId(int id) throws IOException, ClassNotFoundException {
        return Database.carregarFuncionarios().stream()
                .filter(f -> f.getId() == id)
                .findFirst();
    }

    public Optional<Funcionario> buscarPorEmail(String email) throws IOException, ClassNotFoundException {
        return Database.carregarFuncionarios().stream()
                .filter(f -> f.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void salvar(Funcionario funcionario) throws IOException, ClassNotFoundException {
        List<Funcionario> funcionarios = Database.carregarFuncionarios();
        
        // Verifica se já existe um funcionário com o mesmo ID
        Optional<Funcionario> existente = funcionarios.stream()
                .filter(f -> f.getId() == funcionario.getId())
                .findFirst();
        
        if (existente.isPresent()) {
            // Atualiza o funcionário existente
            Funcionario f = existente.get();
            f.setNome(funcionario.getNome());
            f.setEmail(funcionario.getEmail());
            f.setSenha(funcionario.getSenha());
            f.setTelefone(funcionario.getTelefone());
            f.setCargo(funcionario.getCargo());
            f.setAdmin(funcionario.isAdmin());
        } else {
            // Adiciona novo funcionário
            funcionarios.add(funcionario);
        }
        
        Database.salvarFuncionarios(funcionarios);
    }

    public void remover(int id) throws IOException, ClassNotFoundException {
        List<Funcionario> funcionarios = Database.carregarFuncionarios();
        funcionarios.removeIf(f -> f.getId() == id);
        Database.salvarFuncionarios(funcionarios);
    }
}