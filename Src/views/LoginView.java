package views;

import controller.FuncionarioController;
import controller.HospedeController;
import controller.SistemaController;
import models.Funcionario;
import models.Hospede;
// import util.Validadores;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class LoginView {
    private final Scanner scanner;
    private final FuncionarioController funcionarioController;
    private final HospedeController hospedeController;
    private final SistemaController sistemaController;

    public LoginView(Scanner scanner, FuncionarioController funcionarioController, 
                    HospedeController hospedeController, SistemaController sistemaController) {
        this.scanner = scanner;
        this.funcionarioController = funcionarioController;
        this.hospedeController = hospedeController;
        this.sistemaController = sistemaController;
    }

    public void exibirMenuLogin() {
        while (true) {
            System.out.println("\n=== SISTEMA DE POUSADA ===");
            System.out.println("1. Login como Funcionário");
            System.out.println("2. Login como Hóspede");
            // System.out.println("3. Cadastrar-se como Hóspede");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            try {
                switch (opcao) {
                    case 1:
                        loginFuncionario();
                        break;
                    case 2:
                        loginHospede();
                        break;
                    // case 3:
                    //     cadastrarHospede();
                    //     break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao acessar o sistema: " + e.getMessage());
            }
        }
    }

    private void loginFuncionario() throws IOException, ClassNotFoundException {
        System.out.println("\n=== LOGIN FUNCIONÁRIO ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (funcionarioController.autenticarFuncionario(email, senha)) {
            Optional<Funcionario> funcionario = funcionarioController.buscarFuncionarioPorEmail(email);
            if (funcionario.isPresent()) {
                sistemaController.setFuncionarioLogado(funcionario.get());
                MenuView menuView = new MenuView(scanner, sistemaController);
                menuView.exibirMenuFuncionario();
                sistemaController.logout();
            }
        } else {
            System.out.println("Email ou senha incorretos!");
        }
    }

    private void loginHospede() throws IOException, ClassNotFoundException {
        System.out.println("\n=== LOGIN HÓSPEDE ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Optional<Hospede> hospede = hospedeController.buscarHospedePorEmail(email);
        if (hospede.isPresent() && hospede.get().getSenha().equals(senha)) {
            sistemaController.setHospedeLogado(hospede.get());
            MenuView menuView = new MenuView(scanner, sistemaController);
            menuView.exibirMenuHospede();
            sistemaController.logout();
        } else {
            System.out.println("Email ou senha incorretos!");
        }
    }

    // private void cadastrarHospede() throws IOException, ClassNotFoundException {
    //     System.out.println("\n=== CADASTRO DE HÓSPEDE ===");
        
    //     System.out.print("CPF: ");
    //     String cpf = scanner.nextLine();
    //     if (!Validadores.validarCPF(cpf)) {
    //         System.out.println("CPF inválido!");
    //         return;
    //     }

    //     if (hospedeController.buscarHospedePorCpf(cpf).isPresent()) {
    //         System.out.println("CPF já cadastrado!");
    //         return;
    //     }

    //     System.out.print("Nome: ");
    //     String nome = scanner.nextLine();
        
    //     System.out.print("Email: ");
    //     String email = scanner.nextLine();
    //     if (!Validadores.validarEmail(email)) {
    //         System.out.println("Email inválido!");
    //         return;
    //     }

    //     if (hospedeController.buscarHospedePorEmail(email).isPresent()) {
    //         System.out.println("Email já cadastrado!");
    //         return;
    //     }

    //     System.out.print("Senha: ");
    //     String senha = scanner.nextLine();
        
    //     System.out.print("Telefone: ");
    //     String telefone = scanner.nextLine();
    //     if (!Validadores.validarTelefone(telefone)) {
    //         System.out.println("Telefone inválido!");
    //         return;
    //     }

    //     // Gerar ID único
    //     int novoId = hospedeController.buscarTodosHospedes().stream()
    //             .mapToInt(Hospede::getId)
    //             .max()
    //             .orElse(0) + 1;

    //     Hospede novoHospede = new Hospede(novoId, cpf, nome, email, senha, telefone);
    //     hospedeController.cadastrarHospede(novoHospede);
    //     System.out.println("Hóspede cadastrado com sucesso!");
    // }
}