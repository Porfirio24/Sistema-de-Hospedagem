package views;

import controller.HospedeController;
import models.Hospede;
import util.Validadores;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HospedeView {
    private final Scanner scanner;
    private final HospedeController hospedeController;

    public HospedeView(Scanner scanner, HospedeController hospedeController) {
        this.scanner = scanner;
        this.hospedeController = hospedeController;
    }

    public void exibirMenuHospede() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("\n=== MENU HÓSPEDE ===");
            System.out.println("1. Listar Hóspedes");
            System.out.println("2. Cadastrar Hóspede");
            System.out.println("3. Buscar Hóspede por CPF");
            System.out.println("4. Editar Hóspede");
            System.out.println("5. Remover Hóspede");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    listarHospedes();
                    break;
                case 2:
                    cadastrarHospede();
                    break;
                case 3:
                    buscarHospedePorCpf();
                    break;
                case 4:
                    editarHospede();
                    break;
                case 5:
                    removerHospede();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarHospedes() throws IOException, ClassNotFoundException {
        List<Hospede> hospedes = hospedeController.buscarTodosHospedes();
        
        System.out.println("\n=== LISTA DE HÓSPEDES ===");
        System.out.println("ID  | CPF          | Nome           | Email                | Telefone");
        System.out.println("------------------------------------------------------------------");
        
        for (Hospede h : hospedes) {
            System.out.printf("%-3d | %-12s | %-14s | %-20s | %s\n",
                    h.getId(), h.getCpf(), h.getNome(), h.getEmail(), h.getTelefone());
        }
    }

    private void buscarHospedePorCpf() throws IOException, ClassNotFoundException {
        System.out.println("\n=== BUSCAR HÓSPEDE POR CPF ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        Optional<Hospede> hospede = hospedeController.buscarHospedePorCpf(cpf);
        if (hospede.isPresent()) {
            exibirDetalhesHospede(hospede.get());
        } else {
            System.out.println("Hóspede não encontrado!");
        }
    }

    private void editarHospede() throws IOException, ClassNotFoundException {
        System.out.println("\n=== EDITAR HÓSPEDE ===");
        System.out.print("ID do hóspede a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Hospede hospede = hospedeController.buscarHospedePorId(id).orElse(null);
        if (hospede == null) {
            System.out.println("Hóspede não encontrado!");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual");
        
        System.out.printf("CPF [%s]: ", hospede.getCpf());
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty() && Validadores.validarCPF(cpf)) hospede.setCpf(cpf);
        
        System.out.printf("Nome [%s]: ", hospede.getNome());
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) hospede.setNome(nome);
        
        System.out.printf("Email [%s]: ", hospede.getEmail());
        String email = scanner.nextLine();
        if (!email.isEmpty() && Validadores.validarEmail(email)) hospede.setEmail(email);
        
        System.out.print("Nova senha (deixe em branco para manter): ");
        String senha = scanner.nextLine();
        if (!senha.isEmpty()) hospede.setSenha(senha);
        
        System.out.printf("Telefone [%s]: ", hospede.getTelefone());
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty() && Validadores.validarTelefone(telefone)) hospede.setTelefone(telefone);

        hospedeController.atualizarHospede(hospede);
        System.out.println("Hóspede atualizado com sucesso!");
    }

    private void removerHospede() throws IOException, ClassNotFoundException {
        System.out.println("\n=== REMOVER HÓSPEDE ===");
        System.out.print("ID do hóspede a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        hospedeController.removerHospede(id);
        System.out.println("Hóspede removido com sucesso!");
    }

    private void exibirDetalhesHospede(Hospede hospede) {
        System.out.println("\n=== DETALHES DO HÓSPEDE ===");
        System.out.println("ID: " + hospede.getId());
        System.out.println("CPF: " + hospede.getCpf());
        System.out.println("Nome: " + hospede.getNome());
        System.out.println("Email: " + hospede.getEmail());
        System.out.println("Telefone: " + hospede.getTelefone());
    }

    private void cadastrarHospede() throws IOException, ClassNotFoundException {
        System.out.println("\n=== CADASTRO DE HÓSPEDE ===");
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (!Validadores.validarCPF(cpf)) {
            System.out.println("CPF inválido!");
            return;
        }

        if (hospedeController.buscarHospedePorCpf(cpf).isPresent()) {
            System.out.println("CPF já cadastrado!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!Validadores.validarEmail(email)) {
            System.out.println("Email inválido!");
            return;
        }

        if (hospedeController.buscarHospedePorEmail(email).isPresent()) {
            System.out.println("Email já cadastrado!");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        if (!Validadores.validarTelefone(telefone)) {
            System.out.println("Telefone inválido!");
            return;
        }

        // Gerar ID único
        int novoId = hospedeController.buscarTodosHospedes().stream()
                .mapToInt(Hospede::getId)
                .max()
                .orElse(0) + 1;

        Hospede novoHospede = new Hospede(novoId, cpf, nome, email, senha, telefone);
        hospedeController.cadastrarHospede(novoHospede);
        System.out.println("Hóspede cadastrado com sucesso!");
    }
}