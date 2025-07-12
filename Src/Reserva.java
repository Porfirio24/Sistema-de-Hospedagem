public class Reserva {
    private String dataEntrada;
    private String dataSaida;
    private int quantidadeDias;
    private double total;
    private Funcionario funcionario;
    private Hospede hospede;
    private Quarto quarto;

    public Reserva(String dataEntrada, String dataSaida, int quantidadeDias, Funcionario funcionario, Hospede hospede, Quarto quarto) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.quantidadeDias = quantidadeDias; // substituir por cálculo automático com datas futuramente
        this.funcionario = funcionario;
        this.hospede = hospede;
        this.quarto = quarto;
    }

    public double calcularTotal() {
        total = quantidadeDias * quarto.getPrecoDiaria();
        return total;
    }

    public void registrar() {
        if (quarto.estaDisponivel()) {
            quarto.marcarComoOcupado();
            calcularTotal();
            System.out.println("Reserva registrada com sucesso.");
            System.out.println("Total a pagar: R$" + total);
        } else {
            System.out.println("Quarto indisponível.");
        }
    }

    public void cancelar() {
        quarto.marcarComoDisponivel();
        System.out.println("Reserva cancelada.");
    }

    public String getDataEntrada() { 
        return dataEntrada; 
    }

    public void setDataEntrada(String dataEntrada) { 
        this.dataEntrada = dataEntrada; 
    }

    public String getDataSaida() { 
        return dataSaida; 
    }

    public void setDataSaida(String dataSaida) { 
        this.dataSaida = dataSaida; 
    }

    public int getQuantidadeDias() { 
        return quantidadeDias; 
    }

    public void setQuantidadeDias(int quantidadeDias) { 
        this.quantidadeDias = quantidadeDias; 
    }

    public double getTotal() { 
        return total; 
    }

    public void setTotal(double total) { 
        this.total = total; 
    }

    public Funcionario getFuncionario() { 
        return funcionario; 
    }

    public void setFuncionario(Funcionario funcionario) { 
        this.funcionario = funcionario; 
    }

    public Hospede getHospede() { 
        return hospede; 
    }

    public void setHospede(Hospede hospede) { 
        this.hospede = hospede; 
    }

    public Quarto getQuarto() { 
        return quarto; 
    }

    public void setQuarto(Quarto quarto) { 
        this.quarto = quarto; 
    }

    public void exibirDetalhes() {
        System.out.println("Detalhes da Reserva:");
        System.out.println("Data de Entrada: " + dataEntrada);
        System.out.println("Data de Saída: " + dataSaida);
        System.out.println("Quantidade de Dias: " + quantidadeDias);
        System.out.println("Total: R$" + total);
        System.out.println("Funcionário: " + funcionario.getNome());
        System.out.println("Hóspede: " + hospede.getNome());
        System.out.println("Quarto: " + quarto.getNumero() + " - " + quarto.getTipo());
    }
}