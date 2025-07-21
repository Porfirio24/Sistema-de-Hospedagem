package data;

import models.Reserva;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReservaDAO {
    public List<Reserva> buscarTodos() throws IOException, ClassNotFoundException {
        return Database.carregarReservas();
    }

    public Optional<Reserva> buscarPorId(int id) throws IOException, ClassNotFoundException {
        return Database.carregarReservas().stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    public List<Reserva> buscarPorHospede(int idHospede) throws IOException, ClassNotFoundException {
        return Database.carregarReservas().stream()
                .filter(r -> r.getIdHospede() == idHospede)
                .toList();
    }

    public List<Reserva> buscarPorQuarto(int numeroQuarto) throws IOException, ClassNotFoundException {
        return Database.carregarReservas().stream()
                .filter(r -> r.getNumeroQuarto() == numeroQuarto)
                .toList();
    }

    public List<Reserva> buscarPorPeriodo(LocalDate inicio, LocalDate fim) throws IOException, ClassNotFoundException {
        return Database.carregarReservas().stream()
                .filter(r -> !r.getDataSaida().isBefore(inicio) && !r.getDataEntrada().isAfter(fim))
                .toList();
    }

    public void salvar(Reserva reserva) throws IOException, ClassNotFoundException {
        List<Reserva> reservas = Database.carregarReservas();
        
        Optional<Reserva> existente = reservas.stream()
                .filter(r -> r.getId() == reserva.getId())
                .findFirst();
        
        if (existente.isPresent()) {
            Reserva r = existente.get();
            r.setDataEntrada(reserva.getDataEntrada());
            r.setDataSaida(reserva.getDataSaida());
            r.setTotal(reserva.getTotal());
            r.setIdHospede(reserva.getIdHospede());
            r.setNumeroQuarto(reserva.getNumeroQuarto());
            r.setConfirmada(reserva.isConfirmada());
        } else {
            // Gerar novo ID
            int novoId = reservas.stream()
                    .mapToInt(Reserva::getId)
                    .max()
                    .orElse(0) + 1;
            reserva.setId(novoId);
            reservas.add(reserva);
        }
        
        Database.salvarReservas(reservas);
    }

    public void atualizarStatus(int id, boolean confirmada) throws IOException, ClassNotFoundException {
        List<Reserva> reservas = Database.carregarReservas();
        reservas.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .ifPresent(r -> r.setConfirmada(confirmada));
        Database.salvarReservas(reservas);
    }

    public void remover(int id) throws IOException, ClassNotFoundException {
        List<Reserva> reservas = Database.carregarReservas();
        reservas.removeIf(r -> r.getId() == id);
        Database.salvarReservas(reservas);
    }
}