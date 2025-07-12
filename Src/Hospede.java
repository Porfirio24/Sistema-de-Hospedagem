import java.util.ArrayList;
import java.util.List;

/*
 * Classe responsável por representar um hóspede da pousada.
 * Contém informações pessoais e uma lista de reservas feitas por ele.
 */
public class Hospede {

    
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    // Lista com as reservas feitas pelo hóspede
    private List<Reserva> reservas;

    /*
     * Construtor da classe. Recebe os dados principais do hóspede
     * e inicializa a lista de reservas.
     */
    public Hospede(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.reservas = new ArrayList<>();
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    
    public void visualizarDados() {
        System.out.println("===== Dados do Hóspede =====");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
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

    /*
     * Adiciona uma nova reserva à lista do hóspede.
     */
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
}