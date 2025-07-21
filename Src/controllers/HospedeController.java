package controllers;

import data.HospedeDAO;
import models.Hospede;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HospedeController {
    private final HospedeDAO hospedeDAO;

    public HospedeController() {
        this.hospedeDAO = new HospedeDAO();
    }

    public List<Hospede> buscarTodosHospedes() throws IOException, ClassNotFoundException {
        return hospedeDAO.buscarTodos();
    }

    public Optional<Hospede> buscarHospedePorId(int id) throws IOException, ClassNotFoundException {
        return hospedeDAO.buscarPorId(id);
    }

    public Optional<Hospede> buscarHospedePorCpf(String cpf) throws IOException, ClassNotFoundException {
        return hospedeDAO.buscarPorCpf(cpf);
    }

    public Optional<Hospede> buscarHospedePorEmail(String email) throws IOException, ClassNotFoundException {
        return hospedeDAO.buscarPorEmail(email);
    }

    public void cadastrarHospede(Hospede hospede) throws IOException, ClassNotFoundException {
        hospedeDAO.salvar(hospede);
    }
