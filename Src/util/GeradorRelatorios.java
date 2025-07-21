package util;

import models.Quarto;
import models.Reserva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeradorRelatorios {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void gerarRelatorioOcupacao(List<Quarto> quartos, String arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            writer.write("=== RELATÓRIO DE OCUPAÇÃO DE QUARTOS ===\n");
            writer.write(String.format("Gerado em: %s\n\n", LocalDate.now().format(DATE_FORMATTER)));
            
            int totalQuartos = quartos.size();
            long quartosOcupados = quartos.stream().filter(q -> !q.isDisponivel()).count();
            double taxaOcupacao = (double) quartosOcupados / totalQuartos * 100;
            
            writer.write(String.format("Total de quartos: %d\n", totalQuartos));
            writer.write(String.format("Quartos ocupados: %d\n", quartosOcupados));
            writer.write(String.format("Taxa de ocupação: %.2f%%\n\n", taxaOcupacao));
            
            writer.write("Detalhes por quarto:\n");
            writer.write("Número | Tipo     | Preço Diária | Status\n");
            writer.write("-----------------------------------------\n");
            
            for (Quarto quarto : quartos) {
                writer.write(String.format("%-6d | %-8s | R$ %-9.2f | %s\n",
                        quarto.getNumero(),
                        quarto.getTipo(),
                        quarto.getPrecoDiaria(),
                        quarto.isDisponivel() ? "Disponível" : "Ocupado"));
            }
        }
    }

    public static void gerarRelatorioReservas(List<Reserva> reservas, String arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            writer.write("=== RELATÓRIO DE RESERVAS ===\n");
            writer.write(String.format("Gerado em: %s\n\n", LocalDate.now().format(DATE_FORMATTER)));
            
            double faturamentoTotal = reservas.stream()
                    .filter(Reserva::isConfirmada)
                    .mapToDouble(Reserva::getTotal)
                    .sum();
            
            writer.write(String.format("Total de reservas: %d\n", reservas.size()));
            writer.write(String.format("Faturamento total: R$ %.2f\n\n", faturamentoTotal));
            
            writer.write("Detalhes das reservas:\n");
            writer.write("ID  | Quarto | Entrada   | Saída     | Total    | Status\n");
            writer.write("-------------------------------------------------------\n");
            
            for (Reserva reserva : reservas) {
                writer.write(String.format("%-3d | %-6d | %-10s | %-10s | R$ %-6.2f | %s\n",
                        reserva.getId(),
                        reserva.getNumeroQuarto(),
                        reserva.getDataEntrada().format(DATE_FORMATTER),
                        reserva.getDataSaida().format(DATE_FORMATTER),
                        reserva.getTotal(),
                        reserva.isConfirmada() ? "Confirmada" : "Cancelada"));
            }
        }
    }
}