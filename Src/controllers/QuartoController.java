package controllers;

import data.QuartoDAO;
import models.Quarto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class QuartoController {
    private final QuartoDAO quartoDAO;

    public QuartoController() {
        this.quartoDAO = new QuartoDAO();
    }

    public List<Quarto> buscarTodosQuartos() throws IOException, ClassNotFoundException {
        return quartoDAO.buscarTodos();
    }

    public Optional<Quarto> buscarQuartoPorNumero(int numero) throws IOException, ClassNotFoundException {
        return quartoDAO.buscarPorNumero(numero);
    }

    public List<Quarto> buscarQuartosDisponiveis() throws IOException, ClassNotFoundException {
        return quartoDAO.buscarDisponiveis();
    }

    public void cadastrarQuarto(Quarto quarto) throws IOException, ClassNotFoundException {
        quartoDAO.salvar(quarto);
    }

    public void atualizarQuarto(Quarto quarto) throws IOException, ClassNotFoundException {
        quartoDAO.salvar(quarto);
    }

    public void removerQuarto(int numero) throws IOException, ClassNotFoundException {
        quartoDAO.remover(numero);
    }

    public void marcarComoDisponivel(int numero) throws IOException, ClassNotFoundException {
        quartoDAO.marcarDisponibilidade(numero, true);
    }

    public void marcarComoOcupado(int numero) throws IOException, ClassNotFoundException {
        quartoDAO.marcarDisponibilidade(numero, false);
    }
}
