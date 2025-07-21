package data;

import models.Quarto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class QuartoDAO {
    public List<Quarto> buscarTodos() throws IOException, ClassNotFoundException {
        return Database.carregarQuartos();
    }

    public Optional<Quarto> buscarPorNumero(int numero) throws IOException, ClassNotFoundException {
        return Database.carregarQuartos().stream()
                .filter(q -> q.getNumero() == numero)
                .findFirst();
    }

    public List<Quarto> buscarDisponiveis() throws IOException, ClassNotFoundException {
        return Database.carregarQuartos().stream()
                .filter(Quarto::isDisponivel)
                .toList();
    }

    public void salvar(Quarto quarto) throws IOException, ClassNotFoundException {
        List<Quarto> quartos = Database.carregarQuartos();
        
        Optional<Quarto> existente = quartos.stream()
                .filter(q -> q.getNumero() == quarto.getNumero())
                .findFirst();
        
        if (existente.isPresent()) {
            Quarto q = existente.get();
            q.setTipo(quarto.getTipo());
            q.setPrecoDiaria(quarto.getPrecoDiaria());
            q.setDisponivel(quarto.isDisponivel());
        } else {
            quartos.add(quarto);
        }
        
        Database.salvarQuartos(quartos);
    }

    public void remover(int numero) throws IOException, ClassNotFoundException {
        List<Quarto> quartos = Database.carregarQuartos();
        quartos.removeIf(q -> q.getNumero() == numero);
        Database.salvarQuartos(quartos);
    }

    public void marcarDisponibilidade(int numero, boolean disponivel) throws IOException, ClassNotFoundException {
        List<Quarto> quartos = Database.carregarQuartos();
        quartos.stream()
                .filter(q -> q.getNumero() == numero)
                .findFirst()
                .ifPresent(q -> q.setDisponivel(disponivel));
        Database.salvarQuartos(quartos);
    }
}