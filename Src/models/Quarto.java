package Models;

public class Quarto {
    private int id;
    private int numero;
    private String tipo;
    private String status;
    private double preco;

    public Quarto(int id, int numero, String tipo, String status, double preco) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
        this.preco = preco;
    }

    // Construtor sem status (assume "disponível")
    public Quarto(int id, int numero, String tipo, double preco) {
        this(id, numero, tipo, "disponível", preco);
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public String getStatus() { return status; }
    public double getPreco() { return preco; }

    public void setId(int id) { this.id = id; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setStatus(String status) { this.status = status; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return "Quarto [id=" + id + ", número=" + numero + ", tipo=" + tipo + ", status=" + status + ", preço=" + preco + "]";
    }
}

