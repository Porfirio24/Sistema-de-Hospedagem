package Viewss;


import controllers.HospedeDAO;
import controllers.FuncionarioDAO;
import controllers.QuartoDAO;
import controllers.ReservaDAO;

import Models.Hospede;
import Models.Funcionario;
import Models.Quarto;
import Models.Reserva;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuView {
    private Scanner sc = new Scanner(System.in);
    private HospedeDAO hospedeDAO = new HospedeDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private QuartoDAO quartoDAO = new QuartoDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== Sistema de Gestão da Pousada ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Deletar");
            System.out.println("3 - Listar");
            System.out.println("4 - Buscar");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> menuCriacao();
                case 2 -> menuDelecao();
                case 3 -> menuListagem();
                case 4 -> menuBusca();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void menuCriacao() {
        System.out.println("\n--- Menu de Criação ---");
        System.out.println("1 - Hóspede");
        System.out.println("2 - Funcionário");
        System.out.println("3 - Quarto");
        System.out.println("4 - Reserva");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("CPF: ");
                String cpf = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                Hospede h = new Hospede(0, nome, cpf, telefone, email);
                hospedeDAO.inserir(h);
                System.out.println("Hóspede cadastrado!");
            }
            case 2 -> {
                System.out.print("Nome: ");
                String nomeFunc = sc.nextLine();
                System.out.print("Cargo: ");
                String cargo = sc.nextLine();
                System.out.print("Login: ");
                String login = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                Funcionario f = new Funcionario(0, nomeFunc, cargo, login, senha);
                funcionarioDAO.inserir(f);
                System.out.println("Funcionário cadastrado!");
            }
            case 3 -> {
                System.out.print("Número do quarto: ");
                int numero = Integer.parseInt(sc.nextLine());
                System.out.print("Tipo: ");
                String tipo = sc.nextLine();
                System.out.print("Preço por diária: ");
                double preco = Double.parseDouble(sc.nextLine());
                Quarto q = new Quarto(0, numero, tipo, preco);
                quartoDAO.inserir(q);
                System.out.println("Quarto cadastrado!");
            }
            case 4 -> {
                System.out.print("Nome do hóspede: ");
                String NomeHospede = sc.nextLine();
                System.out.print("Número do quarto: ");
                int numeroQuarto = Integer.parseInt(sc.nextLine());
                System.out.print("Data de entrada (YYYY-MM-DD): ");
                LocalDate entrada = LocalDate.parse(sc.nextLine());
                System.out.print("Data de saída (YYYY-MM-DD): ");
                LocalDate saida = LocalDate.parse(sc.nextLine());
                Reserva r = new Reserva(NomeHospede, numeroQuarto, entrada, saida);
                reservaDAO.inserir(r);
            }
            default -> System.out.println("Opção inválida!");
        }
    }
  }