import java.util.ArrayList;
import java.util.List;

/*
 * Classe que representa um hóspede da pousada.
 * Contém dados pessoais e métodos para ações relacionadas ao hóspede.
 */
public class Hospede {

    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    private List<Reserva> reservas;

    public Hospede(int id, String cpf, String nome, String email, String senha, String telefone) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.reservas = new ArrayList<>();
    }

/**
 * Método que simula o cadastro do hóspede, validando os campos obrigatórios.
 */
public boolean cadastrarHospede() {
    if (this.nome == null || this.nome.trim().isEmpty()) {
        System.out.println("Erro: Nome não pode ser vazio.");
        return false;
    }
    if (this.cpf == null || !validarCPF(this.cpf)) {
        System.out.println("Erro: CPF inválido.");
        return false;
    }
    if (this.email == null || !this.email.contains("@")) {
        System.out.println("Erro: E-mail inválido.");
        return false;
    }
    if (this.senha == null || this.senha.length() < 6) {
        System.out.println("Erro: Senha deve ter pelo menos 6 caracteres.");
        return false;
    }

    System.out.println("Hóspede cadastrado com sucesso!");
    return true;
}

/**
 * Valida um CPF verificando se possui 11 dígitos numéricos, 
 * se não é uma sequência repetida e se os dígitos verificadores estão corretos 
 * Retorna true se o CPF for válido; caso contrário, false.
 */
private boolean validarCPF(String cpf) {
    if (cpf == null) return false;

    cpf = cpf.replaceAll("[^\\d]", "");

    if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

    try {
        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            int num = Integer.parseInt(cpf.substring(i, i + 1));
            soma += num * peso--;
        }

        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        if (primeiroDigito != Integer.parseInt(cpf.substring(9, 10))) return false;

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            int num = Integer.parseInt(cpf.substring(i, i + 1));
            soma += num * peso--;
        }

        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return segundoDigito == Integer.parseInt(cpf.substring(10));
    } catch (NumberFormatException e) {
        return false;
    }
}

    
    public void removerHospede() {
        System.out.println("Hóspede removido do sistema.");
    }

    public void visualizarDados() {
        System.out.println("===== Dados do Hóspede =====");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
    }

    public void editarInfos(String novoNome, String novoEmail, String novoTelefone) {
        this.nome = novoNome;
        this.email = novoEmail;
        this.telefone = novoTelefone;
        System.out.println("Informações atualizadas com sucesso.");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    /*
     * Lista todas as reservas feitas por este hóspede.
     * Caso não haja nenhuma, exibe uma mensagem informando.
     */
    public void listarReservas() {
        System.out.println("===== Reservas de " + nome + " =====");
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
        } else {
            for (Reserva reserva : reservas) {
                reserva.exibirDetalhes(); 
            }
        } 
    }
}        