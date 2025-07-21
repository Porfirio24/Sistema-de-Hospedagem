package controllers;

import data.ReservaDAO;
import models.Reserva;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReservaController {
    private final ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public List<Reserva> buscarTodasReservas() throws IOException, ClassNotFoundException {
        return reservaDAO.buscarTodos();
    }

    public Optional<Reserva> buscarReservaPorId(int id) throws IOException, ClassNotFoundException {
        return reservaDAO.buscarPorId(id);
    }

    public List<Reserva> buscarReservasPorHospede(int idHospede) throws IOException, ClassNotFoundException {
        return reservaDAO.buscarPorHospede(idHospede);
    }

    public List<Reserva> buscarReservasPorQuarto(int numeroQuarto) throws IOException, ClassNotFoundException {
        return reservaDAO.buscarPorQuarto(numeroQuarto);
    }

    public List<Reserva> buscarReservasPorPeriodo(LocalDate inicio, LocalDate fim) throws IOException, ClassNotFoundException {
        return reservaDAO.buscarPorPeriodo(inicio, fim);
    }

    public void criarReserva(Reserva reserva) throws IOException, ClassNotFoundException {
        reservaDAO.salvar(reserva);
    }

    public void confirmarReserva(int id) throws IOException, ClassNotFoundException {
        reservaDAO.atualizarStatus(id, true);
    }

    public void cancelarReserva(int id) throws IOException, ClassNotFoundException {
        reservaDAO.atualizarStatus(id, false);
    }

    public void removerReserva(int id) throws IOException, ClassNotFoundException {
        reservaDAO.remover(id);
    }
}