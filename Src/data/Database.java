package data;

import models.Funcionario;
import models.Hospede;
import models.Quarto;
import models.Reserva;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String FUNCIONARIOS_FILE = "funcionarios.txt";
    private static final String HOSPEDES_FILE = "hospedes.txt";
    private static final String QUARTOS_FILE = "quartos.txt";
    private static final String RESERVAS_FILE = "reservas.txt";

    // Método genérico para carregar listas
    @SuppressWarnings("unchecked")
    private static <T> List<T> carregarLista(String filename, List<T> padrao) {
        File file = new File(filename);
        if (!file.exists()) {
            return padrao;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<T>) obj;
            }
            return padrao;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar arquivo " + filename + ": " + e.getMessage());
            return padrao;
        }
    }

    // Método genérico para salvar listas
    private static <T> void salvarLista(String filename, List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(lista);
        }
    }

    // Métodos para Funcionario
    public static void salvarFuncionarios(List<Funcionario> funcionarios) throws IOException {
        salvarLista(FUNCIONARIOS_FILE, funcionarios);
    }

    public static List<Funcionario> carregarFuncionarios() {
        List<Funcionario> padrao = new ArrayList<>();
        padrao.add(new Funcionario(1, "Admin", "admin@pousada.com", "admin123", 
                                 "00000000000", "Gerente", true));
        return carregarLista(FUNCIONARIOS_FILE, padrao);
    }

    // Métodos para Hospede
    public static void salvarHospedes(List<Hospede> hospedes) throws IOException {
        salvarLista(HOSPEDES_FILE, hospedes);
    }

    public static List<Hospede> carregarHospedes() {
        return carregarLista(HOSPEDES_FILE, new ArrayList<>());
    }

    // Métodos para Quarto
    public static void salvarQuartos(List<Quarto> quartos) throws IOException {
        salvarLista(QUARTOS_FILE, quartos);
    }

    public static List<Quarto> carregarQuartos() {
        List<Quarto> padrao = new ArrayList<>();
        padrao.add(new Quarto(101, "Simples", 150.0, true));
        padrao.add(new Quarto(102, "Simples", 150.0, true));
        padrao.add(new Quarto(201, "Duplo", 250.0, true));
        padrao.add(new Quarto(202, "Duplo", 250.0, true));
        padrao.add(new Quarto(301, "Suíte", 400.0, true));
        return carregarLista(QUARTOS_FILE, padrao);
    }

    // Métodos para Reserva
    public static void salvarReservas(List<Reserva> reservas) throws IOException {
        salvarLista(RESERVAS_FILE, reservas);
    }

    public static List<Reserva> carregarReservas() {
        return carregarLista(RESERVAS_FILE, new ArrayList<>());
    }
}