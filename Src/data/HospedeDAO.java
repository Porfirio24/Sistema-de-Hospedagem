package data;

import models.Hospede;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HospedeDAO {
    public List<Hospede> buscarTodos() throws IOException, ClassNotFoundException {
        return Database.carregarHospedes();
    }

    public Optional<Hospede> buscarPorId(int id) throws IOException, ClassNotFoundException {
        return Database.carregarHospedes().stream()
                .filter(h -> h.getId() == id)
                .findFirst();
    }

    public Optional<Hospede> buscarPorCpf(String cpf) throws IOException, ClassNotFoundException {
        return Database.carregarHospedes().stream()
                .filter(h -> h.getCpf().equals(cpf))
                .findFirst();
    }

    public Optional<Hospede> buscarPorEmail(String email) throws IOException, ClassNotFoundException {
        return Database.carregarHospedes().stream()
                .filter(h -> h.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void salvar(Hospede hospede) throws IOException, ClassNotFoundException {
        List<Hospede> hospedes = Database.carregarHospedes();
        
        Optional<Hospede> existente = hospedes.stream()
                .filter(h -> h.getId() == hospede.getId())
                .findFirst();
        
        if (existente.isPresent()) {
            Hospede h = existente.get();
            h.setCpf(hospede.getCpf());
            h.setNome(hospede.getNome());
            h.setEmail(hospede.getEmail());
            h.setTelefone(hospede.getTelefone());
        } else {
            hospedes.add(hospede);
        }
        
        Database.salvarHospedes(hospedes);
    }

    public void remover(int id) throws IOException, ClassNotFoundException {
        List<Hospede> hospedes = Database.carregarHospedes();
        hospedes.removeIf(h -> h.getId() == id);
        Database.salvarHospedes(hospedes);
    }
}