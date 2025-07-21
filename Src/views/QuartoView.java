package views;

import models.Quarto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import controllers.QuartoController;

public class QuartoView {
    private final Scanner scanner;
    private final QuartoController quartoController;

    public QuartoView(Scanner scanner, QuartoController quartoController) {
        this.scanner = scanner;
        this.quartoController = quartoController;
    }

    public void exibirMenuQuarto() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("\n=== MENU QUARTO ===");
            System.out.println("1. Listar Todos os Quartos");
            System.out.println("2. Listar Quartos Disponíveis");
            System.out.println("3. Buscar Quarto por Número");
            System.out.println("4. Cadastrar Quarto");
            System.out.println("5. Editar Quarto");
            System.out.println("6. Remover Quarto");
            System.out.println("7. Marcar como Disponível");
            System.out.println("8. Marcar como Ocupado");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    listarTodosQuartos();
                    break;
                case 2:
                    listarQuartosDisponiveis();
                    break;
                case 3:
                    buscarQuartoPorNumero();
                    break;
                case 4:
                    cadastrarQuarto();
                    break;
                case 5:
                    editarQuarto();
                    break;
                case 6:
                    removerQuarto();
                    break;
                case 7:
                    marcarComoDisponivel();
                    break;
                case 8:
                    marcarComoOcupado();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarTodosQuartos() throws IOException, ClassNotFoundException {
        List<Quarto> quartos = quartoController.buscarTodosQuartos();
        exibirListaQuartos(quartos, "TODOS OS QUARTOS");
    }

    public void listarQuartosDisponiveis() throws IOException, ClassNotFoundException {
        List<Quarto> quartos = quartoController.buscarQuartosDisponiveis();
        exibirListaQuartos(quartos, "QUARTOS DISPONÍVEIS");
    }

    private void buscarQuartoPorNumero() throws IOException, ClassNotFoundException {
        System.out.println("\n=== BUSCAR QUARTO ===");
        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Optional<Quarto> quarto = quartoController.buscarQuartoPorNumero(numero);
        if (quarto.isPresent()) {
            exibirDetalhesQuarto(quarto.get());
        } else {
            System.out.println("Quarto não encontrado!");
        }
    }

    private void cadastrarQuarto() throws IOException, ClassNotFoundException {
        System.out.println("\n=== CADASTRAR QUARTO ===");
        
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Tipo (Simples/Duplo/Suíte): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Preço da diária: ");
        double precoDiaria = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Disponível? (S/N): ");
        boolean disponivel = scanner.nextLine().equalsIgnoreCase("S");

        Quarto novoQuarto = new Quarto(numero, tipo, precoDiaria, disponivel);
        quartoController.cadastrarQuarto(novoQuarto);
        System.out.println("Quarto cadastrado com sucesso!");
    }

    private void editarQuarto() throws IOException, ClassNotFoundException {
        System.out.println("\n=== EDITAR QUARTO ===");
        System.out.print("Número do quarto a editar: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Quarto quarto = quartoController.buscarQuartoPorNumero(numero).orElse(null);
        if (quarto == null) {
            System.out.println("Quarto não encontrado!");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual");
        
        System.out.printf("Tipo [%s]: ", quarto.getTipo());
        String tipo = scanner.nextLine();
        if (!tipo.isEmpty()) quarto.setTipo(tipo);
        
        System.out.printf("Preço da diária [%.2f]: ", quarto.getPrecoDiaria());
        String precoStr = scanner.nextLine();
        if (!precoStr.isEmpty()) quarto.setPrecoDiaria(Double.parseDouble(precoStr));
        
        System.out.printf("Disponível? [%s] (S/N): ", quarto.isDisponivel() ? "Sim" : "Não");
        String dispStr = scanner.nextLine();
        if (!dispStr.isEmpty()) quarto.setDisponivel(dispStr.equalsIgnoreCase("S"));

        quartoController.atualizarQuarto(quarto);
        System.out.println("Quarto atualizado com sucesso!");
    }

    private void removerQuarto() throws IOException, ClassNotFoundException {
        System.out.println("\n=== REMOVER QUARTO ===");
        System.out.print("Número do quarto a remover: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        quartoController.removerQuarto(numero);
        System.out.println("Quarto removido com sucesso!");
    }

    private void marcarComoDisponivel() throws IOException, ClassNotFoundException {
        System.out.println("\n=== MARCAR QUARTO COMO DISPONÍVEL ===");
        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        quartoController.marcarComoDisponivel(numero);
        System.out.println("Quarto marcado como disponível!");
    }

    private void marcarComoOcupado() throws IOException, ClassNotFoundException {
        System.out.println("\n=== MARCAR QUARTO COMO OCUPADO ===");
        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        quartoController.marcarComoOcupado(numero);
        System.out.println("Quarto marcado como ocupado!");
    }

    private void exibirListaQuartos(List<Quarto> quartos, String titulo) {
        System.out.println("\n=== " + titulo + " ===");
        System.out.println("Número | Tipo     | Preço Diária | Status");
        System.out.println("-----------------------------------------");
        
        for (Quarto quarto : quartos) {
            System.out.printf("%-6d | %-8s | R$ %-9.2f | %s\n",
                    quarto.getNumero(),
                    quarto.getTipo(),
                    quarto.getPrecoDiaria(),
                    quarto.isDisponivel() ? "Disponível" : "Ocupado");
        }
    }

    private void exibirDetalhesQuarto(Quarto quarto) {
        System.out.println("\n=== DETALHES DO QUARTO ===");
        System.out.println("Número: " + quarto.getNumero());
        System.out.println("Tipo: " + quarto.getTipo());
        System.out.println("Preço da diária: R$ " + quarto.getPrecoDiaria());
        System.out.println("Status: " + (quarto.isDisponivel() ? "Disponível" : "Ocupado"));
    }
}