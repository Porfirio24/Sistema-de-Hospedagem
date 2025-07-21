package views;

import controller.HospedeController;
import controller.QuartoController;
import controller.ReservaController;
import models.Reserva;
import util.DataUtil;
import models.Quarto;

import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReservaView {
    private final Scanner scanner;
    private final ReservaController reservaController;
    private final QuartoController quartoController;
    // private final HospedeController hospedeController;

    public ReservaView(Scanner scanner, ReservaController reservaController,
                     QuartoController quartoController, HospedeController hospedeController) {
        this.scanner = scanner;
        this.reservaController = reservaController;
        this.quartoController = quartoController;
        // this.hospedeController = hospedeController;
    }

    public void exibirMenuReserva() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("\n=== MENU RESERVA ===");
            System.out.println("1. Listar Todas as Reservas");
            System.out.println("2. Listar Reservas por Hóspede");
            System.out.println("3. Listar Reservas por Quarto");
            System.out.println("4. Listar Reservas por Período");
            System.out.println("5. Criar Reserva");
            System.out.println("6. Confirmar Reserva");
            System.out.println("7. Cancelar Reserva");
            System.out.println("8. Remover Reserva");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    listarTodasReservas();
                    break;
                case 2:
                    listarReservasPorHospede();
                    break;
                case 3:
                    listarReservasPorQuarto();
                    break;
                case 4:
                    listarReservasPorPeriodo();
                    break;
                case 5:
                    criarReserva();
                    break;
                case 6:
                    confirmarReserva();
                    break;
                case 7:
                    cancelarReserva();
                    break;
                case 8:
                    removerReserva();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarTodasReservas() throws IOException, ClassNotFoundException {
        List<Reserva> reservas = reservaController.buscarTodasReservas();
        exibirListaReservas(reservas, "TODAS AS RESERVAS");
    }

    public void listarReservasPorHospede() throws IOException, ClassNotFoundException {
        System.out.println("\n=== RESERVAS POR HÓSPEDE ===");
        System.out.print("ID do hóspede: ");
        int idHospede = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        List<Reserva> reservas = reservaController.buscarReservasPorHospede(idHospede);
        exibirListaReservas(reservas, "RESERVAS DO HÓSPEDE " + idHospede);
    }

    private void listarReservasPorQuarto() throws IOException, ClassNotFoundException {
        System.out.println("\n=== RESERVAS POR QUARTO ===");
        System.out.print("Número do quarto: ");
        int numeroQuarto = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        List<Reserva> reservas = reservaController.buscarReservasPorQuarto(numeroQuarto);
        exibirListaReservas(reservas, "RESERVAS DO QUARTO " + numeroQuarto);
    }

    private void listarReservasPorPeriodo() throws IOException, ClassNotFoundException {
        System.out.println("\n=== RESERVAS POR PERÍODO ===");
        System.out.print("Data de início (dd/mm/aaaa): ");
        LocalDate inicio = DataUtil.stringParaData(scanner.nextLine());
        
        System.out.print("Data de fim (dd/mm/aaaa): ");
        LocalDate fim = DataUtil.stringParaData(scanner.nextLine());

        List<Reserva> reservas = reservaController.buscarReservasPorPeriodo(inicio, fim);
        exibirListaReservas(reservas, "RESERVAS ENTRE " + DataUtil.dataParaString(inicio) + 
                           " E " + DataUtil.dataParaString(fim));
    }

    public void criarReserva() throws IOException, ClassNotFoundException {
        System.out.println("\n=== CRIAR RESERVA ===");
        
        System.out.print("ID do hóspede: ");
        int idHospede = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Número do quarto: ");
        int numeroQuarto = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Data de entrada (dd/mm/aaaa): ");
        LocalDate entrada = DataUtil.stringParaData(scanner.nextLine());
        
        System.out.print("Data de saída (dd/mm/aaaa): ");
        LocalDate saida = DataUtil.stringParaData(scanner.nextLine());

        // Verificar se o quarto está disponível
        Optional<Quarto> quarto = quartoController.buscarQuartoPorNumero(numeroQuarto);
        if (quarto.isEmpty() || !quarto.get().isDisponivel()) {
            System.out.println("Quarto não disponível para reserva!");
            return;
        }

        // Calcular total
        long dias = ChronoUnit.DAYS.between(entrada, saida);
        double total = dias * quarto.get().getPrecoDiaria();

        // Criar reserva (inicialmente não confirmada)
        Reserva novaReserva = new Reserva(0, entrada, saida, total, idHospede, numeroQuarto, false);
        reservaController.criarReserva(novaReserva);
        System.out.println("Reserva criada com sucesso! Total: R$ " + total);
    }

    private void confirmarReserva() throws IOException, ClassNotFoundException {
        System.out.println("\n=== CONFIRMAR RESERVA ===");
        System.out.print("ID da reserva: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        reservaController.confirmarReserva(id);
        System.out.println("Reserva confirmada com sucesso!");
    }

    private void cancelarReserva() throws IOException, ClassNotFoundException {
        System.out.println("\n=== CANCELAR RESERVA ===");
        System.out.print("ID da reserva: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        reservaController.cancelarReserva(id);
        System.out.println("Reserva cancelada com sucesso!");
    }

    private void removerReserva() throws IOException, ClassNotFoundException {
        System.out.println("\n=== REMOVER RESERVA ===");
        System.out.print("ID da reserva: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        reservaController.removerReserva(id);
        System.out.println("Reserva removida com sucesso!");
    }

    public void exibirListaReservas(List<Reserva> reservas, String titulo) {
        System.out.println("\n=== " + titulo + " ===");
        System.out.println("ID  | Quarto | Hóspede | Entrada   | Saída     | Total    | Status");
        System.out.println("------------------------------------------------------------------");
        
        for (Reserva reserva : reservas) {
            System.out.printf("%-3d | %-6d | %-7d | %-10s | %-10s | R$ %-6.2f | %s\n",
                    reserva.getId(),
                    reserva.getNumeroQuarto(),
                    reserva.getIdHospede(),
                    DataUtil.dataParaString(reserva.getDataEntrada()),
                    DataUtil.dataParaString(reserva.getDataSaida()),
                    reserva.getTotal(),
                    reserva.isConfirmada() ? "Confirmada" : "Pendente");
        }
    }
}