public class Quarto {
    private int numero;
    private String tipo;
    private double precoDiaria;
    private boolean disponivel;

    public Quarto(int numero, String tipo, double precoDiaria){
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.disponivel = true; // o quarto recebe o valor "true", pois inicialmente estará disponivel
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public  String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public double getPrecoDiaria(){
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria){
        this.precoDiaria = precoDiaria;
    }

    public boolean estaDisponivel(){
        return disponivel;
    }

    public void marcarComoDisponivel(){
        this.disponivel = true;
    }

    public void marcarComoOcupado(){
        this.disponivel = false;
    }
    
    public static Quarto criarQuarto(int numero, String tipo, double precoDiaria) {
        return new Quarto(numero, tipo, precoDiaria);
    }

    public void informarDados() {
        System.out.println("===== Dados do Quarto =====");
        System.out.println("Número: " + numero);
        System.out.println("Tipo: " + tipo);
        System.out.println("Preço Diária: R$" + precoDiaria);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }

    public static void listarQuartos(List<Quarto> quartos) {
        System.out.println("===== Lista de Quartos =====");
        for (Quarto q : quartos) {
            q.informarDados();
        }
    }
}