
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private int id;
    private String nomeHospede;
    private int numeroQuarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private double valorTotal;

    public Reserva(int id, String nomeHospede, int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida, double valorTotal) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
    }

    public Reserva(String nomeHospede, int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.nomeHospede = nomeHospede;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public void calcularValorTotal(double precoDiaria) {
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        if (dias <= 0) dias = 1;
        this.valorTotal = precoDiaria * dias;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNomeHospede() { return nomeHospede; }
    public int getNumeroQuarto() { return numeroQuarto; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }
    public double getValorTotal() { return valorTotal; }

    public void setId(int id) { this.id = id; }
    public void setNomeHospede(String nomeHospede) { this.nomeHospede = nomeHospede; }
    public void setNumeroQuarto(int numeroQuarto) { this.numeroQuarto = numeroQuarto; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }
    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return String.format("Reserva [ID: %d | Hóspede: %s | Quarto: %d | Entrada: %s | Saída: %s | Total: R$ %.2f]",
                id, nomeHospede, numeroQuarto, dataEntrada, dataSaida, valorTotal);
    }
}