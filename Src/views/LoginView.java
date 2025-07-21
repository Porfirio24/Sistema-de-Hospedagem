package views;

import models.Funcionario;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import controllers.FuncionarioController;
import controllers.HospedeController;
import controllers.SistemaController;

public class LoginView {
    private final Scanner scanner;
    private final FuncionarioController funcionarioController;
    private final SistemaController sistemaController;

    public LoginView(Scanner scanner, FuncionarioController funcionarioController, 
                    HospedeController hospedeController, SistemaController sistemaController) {
        this.scanner = scanner;
        this.funcionarioController = funcionarioController;
        this.sistemaController = sistemaController;
    }

    public void exibirMenuLogin() {
        while (true) {
            System.out.println("\n=== SISTEMA DE POUSADA ===");
            System.out.println("1. Login como Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            try {
                switch (opcao) {
                    case 1:
                        loginFuncionario();
                        break;
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
}