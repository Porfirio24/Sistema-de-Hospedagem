package views;

import controllers.HospedeDAO;
import controllers.FuncionarioDAO;
import controllers.QuartoDAO;
import controllers.ReservaDAO;

import models.Hospede;
import models.Funcionario;
import models.Quarto;
import models.Reserva;

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

    private void menuDelecao() {
        System.out.println("\n--- Menu de Deleção ---");
        System.out.println("1 - Hóspede");
        System.out.println("2 - Funcionário");
        System.out.println("3 - Quarto");
        System.out.println("4 - Finalizar Reserva");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1 -> {
                System.out.print("ID do hóspede: ");
                int idHospede = Integer.parseInt(sc.nextLine());
                hospedeDAO.deletar(idHospede);
            }
            case 2 -> {
                System.out.print("ID do funcionário: ");
                int idFunc = Integer.parseInt(sc.nextLine());
                funcionarioDAO.deletar(idFunc);
                System.out.println("Funcionário deletado.");
            }
            case 3 -> {
                System.out.print("ID do quarto: ");
                int idQuarto = Integer.parseInt(sc.nextLine());
                quartoDAO.deletar(idQuarto);
                System.out.println("Quarto deletado.");
            }
            case 4 -> {
                System.out.print("ID da reserva a finalizar: ");
                int idReserva = Integer.parseInt(sc.nextLine());
                reservaDAO.finalizarReserva(idReserva);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void menuListagem() {
        System.out.println("\n--- Menu de Listagem ---");
        System.out.println("1 - Hóspedes");
        System.out.println("2 - Funcionários");
        System.out.println("3 - Quartos");
        System.out.println("4 - Reservas");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1 -> {
                System.out.println("\n--- Lista de Hóspedes ---");
                hospedeDAO.listar().forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("\n--- Lista de Funcionários ---");
                funcionarioDAO.listar().forEach(System.out::println);
            }
            case 3 -> {
                System.out.println("\n--- Lista de Quartos ---");
                quartoDAO.listar().forEach(System.out::println);
            }
            case 4 -> {
                System.out.println("\n--- Lista de Reservas ---");
                reservaDAO.listar().forEach(System.out::println);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void menuBusca() {
    System.out.println("\n--- Menu de Busca ---");
    System.out.println("1 - Buscar hóspede por CPF");
    System.out.println("2 - Buscar quarto por número");
    System.out.println("3 - Buscar funcionário por login");
    System.out.print("Opção: ");
    int opcao = Integer.parseInt(sc.nextLine());

    switch (opcao) {
        case 1:
            System.out.print("Digite o CPF do hóspede: ");
            String cpf = sc.nextLine();
            Hospede h = hospedeDAO.buscarPorCPF(cpf);
            if (h != null) {
            System.out.println("Hóspede encontrado:");
            System.out.println(h);

            System.out.print("Deseja editar as informações? (s/n): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                System.out.println("Qual informação deseja editar?");
                System.out.println("1 - Nome");
                System.out.println("2 - Email");
                System.out.println("3 - Telefone");
                System.out.print("Opção (1/2/3): ");
                String inputOpcao = sc.nextLine();

                int escolha;
                try {
                    escolha = Integer.parseInt(inputOpcao);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite apenas 1, 2 ou 3.");
                    return;
                }

                String campo = "";

                switch (escolha) {
                    case 1:
                        campo = "nome";
                        break;
                    case 2:
                        campo = "email";
                        break;
                    case 3:
                        campo = "telefone";
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        return;
                }

                System.out.print("Novo valor para " + campo + ": ");
                String novoValor = sc.nextLine();
                hospedeDAO.atualizarCampo(h.getId(), campo, novoValor);
        }
            } else {
                System.out.println("Hóspede não encontrado.");
            }
            break;

        case 2:
            System.out.print("Número do quarto: ");
            int numero = Integer.parseInt(sc.nextLine());
            Quarto q = quartoDAO.buscarPorNumero(numero);

            if (q != null) {
                System.out.println("Quarto encontrado:");
                System.out.println(q);

                System.out.print("Deseja editar esse quarto? (s/n): ");
                String resp = sc.nextLine();
                if (resp.equalsIgnoreCase("s")) {
                    System.out.println("O que deseja editar?");
                    System.out.println("1 - Tipo");
                    System.out.println("2 - Preço");
                    System.out.print("Opção (1/2): ");
                    String opcaoStr = sc.nextLine();
                    
                    try {
                        opcao = Integer.parseInt(opcaoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                        return;
                    }

                    switch (opcao) {
                        case 1 -> {
                            System.out.print("Novo tipo: ");
                            String novoTipo = sc.nextLine();
                            quartoDAO.atualizarCampo(q.getId(), "tipo", novoTipo);
                        }
                        case 2 -> {
                            System.out.print("Novo preço: ");
                            try {
                                double novoPreco = Double.parseDouble(sc.nextLine());
                                quartoDAO.atualizarCampo(q.getId(), "preco", novoPreco);
                            } catch (NumberFormatException e) {
                                System.out.println("Preço inválido.");
                            }
                        }
                        default -> System.out.println("Opção inválida.");
                    }
                }
            } else {
                System.out.println("Quarto não encontrado.");
        }
            break;

        case 3:
            System.out.print("Digite o login do funcionário: ");
            String login = sc.nextLine();
            Funcionario f = funcionarioDAO.buscarPorLogin(login);
            if (f != null) {
                System.out.println(f);
            } else {
                System.out.println("Funcionário não encontrado.");
            }
            break;

        default:
            System.out.println("Opção inválida!");
    }
}
}