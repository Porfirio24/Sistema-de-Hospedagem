
import java.util.List;

/*
 * Classe que representa um funcionário da pousada.
 * Também responsável por cadastrar, remover e listar hóspedes.
 */
public class Funcionario {

    private String nome;
    private String login;
    private String senha;
    private String cargo;

    public Funcionario(String nome, String login, String senha, String cargo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    
    public boolean cadastrarHospede(Hospede hospede, List<Hospede> lista) {
        if (hospede == null) {
            System.out.println("[ERRO] Hóspede nulo.");
            return false;
        }

        if (!hospede.validarDados()) {
            System.out.println("[ERRO] Dados do hóspede inválidos.");
            return false;
        }else{
         System.out.println("Dados Validos!");
        }

        lista.add(hospede);
        System.out.println("[OK] Hóspede " + hospede.getNome() + " cadastrado pelo funcionário " + nome);
        return true;
    }

    
    public boolean removerHospede(Hospede hospede, List<Hospede> lista) {
        if (!lista.contains(hospede)) {
            System.out.println("[ERRO] Hóspede não encontrado.");
            return false;
        }

        if (!hospede.getReservas().isEmpty()) {
            System.out.println("[ERRO] Hóspede possui reservas ativas.");
            return false;
        }

        lista.remove(hospede);
        System.out.println("[OK] Hóspede " + hospede.getNome() + " removido pelo funcionário " + nome);
        return true;
    }

    
    public void listarHospedes(List<Hospede> lista) {
        if (lista.isEmpty()) {
            System.out.println("[INFO] Nenhum hóspede cadastrado.");
            return;
        }

        System.out.println("===== Hóspedes cadastrados (por " + nome + ") =====");
        for (Hospede h : lista) {
            h.visualizarDados();
            System.out.println("----------------------------------");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}