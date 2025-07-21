package views;

import models.Funcionario;
import util.GeradorRelatorios;
import models.Quarto;  
import models.Reserva; 

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controllers.SistemaController;

public class MenuView {
    private final Scanner scanner;
    private final SistemaController sistemaController;

    public MenuView(Scanner scanner, SistemaController sistemaController) {
        this.scanner = scanner;
        this.sistemaController = sistemaController;
    }

    public void exibirMenuFuncionario() throws IOException, ClassNotFoundException {
        Funcionario funcionario = sistemaController.getFuncionarioLogado();
        boolean isAdmin = funcionario.isAdmin();
        
        FuncionarioView funcionarioView = new FuncionarioView(scanner, sistemaController.getFuncionarioController());
        HospedeView hospedeView = new HospedeView(scanner, sistemaController.getHospedeController());
        QuartoView quartoView = new QuartoView(scanner, sistemaController.getQuartoController());
        ReservaView reservaView = new ReservaView(scanner, sistemaController.getReservaController(),
                sistemaController.getQuartoController(), sistemaController.getHospedeController());

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Funcionários");
            System.out.println("2. Gerenciar Hóspedes");
            System.out.println("3. Gerenciar Quartos");
            System.out.println("4. Gerenciar Reservas");
            if (isAdmin) {
                System.out.println("5. Gerar Relatórios");
            }
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    funcionarioView.exibirMenuFuncionario(isAdmin);
                    break;
                case 2:
                    hospedeView.exibirMenuHospede();
                    break;
                case 3:
                    quartoView.exibirMenuQuarto();
                    break;
                case 4:
                    reservaView.exibirMenuReserva();
                    break;
                case 5:
                    if (isAdmin) {
                        gerarRelatorios();
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 0:
                    sistemaController.logout();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void gerarRelatorios() throws IOException, ClassNotFoundException {
        System.out.println("\n=== GERAR RELATÓRIOS ===");
        System.out.println("1. Relatório de Ocupação");
        System.out.println("2. Relatório de Reservas");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                List<Quarto> quartos = sistemaController.getQuartoController().buscarTodosQuartos();
                GeradorRelatorios.gerarRelatorioOcupacao(quartos, "relatorio_ocupacao.txt");
                System.out.println("Relatório de ocupação gerado em relatorio_ocupacao.txt");
                break;
            case 2:
                List<Reserva> reservas = sistemaController.getReservaController().buscarTodasReservas();
                GeradorRelatorios.gerarRelatorioReservas(reservas, "relatorio_reservas.txt");
                System.out.println("Relatório de reservas gerado em relatorio_reservas.txt");
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}