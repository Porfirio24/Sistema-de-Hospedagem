import java.util.ArrayList;
import java.util.List;

public class Quarto {
    private int numero;
    private String tipo;
    private double precoDiaria;
    private boolean disponivel;

    public Quarto(int numero, String tipo, double precoDiaria){
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.disponivel = true; // o quarto recebe o valor "true" pois sempre estara disponivel
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

    public void serPrecoDiaria(){
        this.precoDiaria = precoDiaria;
    }

    public boolean Disponivel(){
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
}
