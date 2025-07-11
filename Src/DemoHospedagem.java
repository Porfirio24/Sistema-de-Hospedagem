
public class DemoHospedagem {
    public static void main(String[] args) {
        Quarto quarto1 = Quarto.criarQuarto(01, "Solteiro", 150.00);
        Quarto quarto2 = Quarto.criarQuarto(02, "Cama Dupla", 100.00);
        System.out.println("Quarto Criado: " + quarto1.getNumero() + ", Tipo: " + quarto1.getTipo() + ", Preço: " + quarto1.getPrecoDiaria());
        System.out.println("Quarto Criado: " + quarto2.getNumero() + ", Tipo: " + quarto2.getTipo() + ", Preço: " + quarto2.getPrecoDiaria());
    }
}
