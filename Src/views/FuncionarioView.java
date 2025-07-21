package views;

import models.Funcionario;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controllers.FuncionarioController;

public class FuncionarioView {
    private final Scanner scanner;
    private final FuncionarioController funcionarioController;

    public FuncionarioView(Scanner scanner, FuncionarioController funcionarioController) {
        this.scanner = scanner;
        this.funcionarioController = funcionarioController;
    }

    public void exibirMenuFuncionario(boolean isAdmin) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("\n=== MENU FUNCIONÁRIO ===");
            System.out.println("1. Listar Funcionários");
            if (isAdmin) {
                System.out.println("2. Cadastrar Funcionário");
                System.out.println("3. Editar Funcionário");
                System.out.println("4. Remover Funcionário");
            }
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    listarFuncionarios();
                    break;
                case 2:
                    if (isAdmin) cadastrarFuncionario();
                    else System.out.println("Opção inválida!");
                    break;
                case 3:
                    if (isAdmin) editarFuncionario();
                    else System.out.println("Opção inválida!");
                    break;
                case 4:
                    if (isAdmin) removerFuncionario();
                    else System.out.println("Opção inválida!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarFuncionarios() throws IOException, ClassNotFoundException {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        System.out.println("ID  | Nome           | Email                | Cargo      | Admin");
        System.out.println("-------------------------------------------------------------");
        
        for (Funcionario f : funcionarios) {
            System.out.printf("%-3d | %-14s | %-20s | %-10s | %s\n",
                    f.getId(), f.getNome(), f.getEmail(), f.getCargo(), f.isAdmin() ? "Sim" : "Não");
        }
    }

    private void cadastrarFuncionario() throws IOException, ClassNotFoundException {
        System.out.println("\n=== CADASTRAR FUNCIONÁRIO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        
        System.out.print("É admin? (S/N): ");
        boolean isAdmin = scanner.nextLine().equalsIgnoreCase("S");

        // Gerar ID
        int novoId = funcionarioController.listarFuncionarios().stream()
                .mapToInt(Funcionario::getId)
                .max()
                .orElse(0) + 1;

        Funcionario novoFuncionario = new Funcionario(novoId, nome, email, senha, telefone, cargo, isAdmin);
        funcionarioController.cadastrarFuncionario(novoFuncionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void editarFuncionario() throws IOException, ClassNotFoundException {
        System.out.println("\n=== EDITAR FUNCIONÁRIO ===");
        System.out.print("ID do funcionário a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Funcionario funcionario = funcionarioController.buscarFuncionarioPorId(id).orElse(null);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual");
        
        System.out.printf("Nome [%s]: ", funcionario.getNome());
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) funcionario.setNome(nome);
        
        System.out.printf("Email [%s]: ", funcionario.getEmail());
        String email = scanner.nextLine();
        if (!email.isEmpty()) funcionario.setEmail(email);
        
        System.out.print("Nova senha (deixe em branco para manter): ");
        String senha = scanner.nextLine();
        if (!senha.isEmpty()) funcionario.setSenha(senha);
        
        System.out.printf("Telefone [%s]: ", funcionario.getTelefone());
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) funcionario.setTelefone(telefone);
        
        System.out.printf("Cargo [%s]: ", funcionario.getCargo());
        String cargo = scanner.nextLine();
        if (!cargo.isEmpty()) funcionario.setCargo(cargo);
        
        System.out.printf("É admin? [%s] (S/N): ", funcionario.isAdmin() ? "Sim" : "Não");
        String adminStr = scanner.nextLine();
        if (!adminStr.isEmpty()) {
            funcionario.setAdmin(adminStr.equalsIgnoreCase("S"));
        }

        funcionarioController.atualizarFuncionario(funcionario);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    private void removerFuncionario() throws IOException, ClassNotFoundException {
        System.out.println("\n=== REMOVER FUNCIONÁRIO ===");
        System.out.print("ID do funcionário a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        funcionarioController.removerFuncionario(id);
        System.out.println("Funcionário removido com sucesso!");
    }
}