package controllers;

import data.FuncionarioDAO;
import models.Funcionario;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FuncionarioController {
    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public List<Funcionario> listarFuncionarios() throws IOException, ClassNotFoundException {
        return funcionarioDAO.buscarTodos();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(int id) throws IOException, ClassNotFoundException {
        return funcionarioDAO.buscarPorId(id);
    }

    public Optional<Funcionario> buscarFuncionarioPorEmail(String email) throws IOException, ClassNotFoundException {
        return funcionarioDAO.buscarPorEmail(email);
    }

    public void cadastrarFuncionario(Funcionario funcionario) throws IOException, ClassNotFoundException {
        funcionarioDAO.salvar(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) throws IOException, ClassNotFoundException {
        funcionarioDAO.salvar(funcionario);
    }

    public void removerFuncionario(int id) throws IOException, ClassNotFoundException {
        funcionarioDAO.remover(id);
    }

    public boolean autenticarFuncionario(String email, String senha) throws IOException, ClassNotFoundException {
        Optional<Funcionario> funcionario = funcionarioDAO.buscarPorEmail(email);
        return funcionario.isPresent() && funcionario.get().getSenha().equals(senha);
    }
}