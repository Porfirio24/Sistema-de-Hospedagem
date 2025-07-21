package models;

import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;

public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private double total;
    private int idHospede;
    private int numeroQuarto;
    private boolean confirmada;

    public Reserva(int id, LocalDate dataEntrada, LocalDate dataSaida, double total, 
                   int idHospede, int numeroQuarto, boolean confirmada) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.total = total;
        this.idHospede = idHospede;
        this.numeroQuarto = numeroQuarto;
        this.confirmada = confirmada;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", total=" + total +
                ", idHospede=" + idHospede +
                ", numeroQuarto=" + numeroQuarto +
                ", confirmada=" + confirmada +
                '}';
    }
}