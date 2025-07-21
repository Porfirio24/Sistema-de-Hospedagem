package controllers;

import models.Funcionario;
import models.Hospede;

public class SistemaController {
    private final FuncionarioController funcionarioController;
    private final HospedeController hospedeController;
    private final QuartoController quartoController;
    private final ReservaController reservaController;
    
    private Funcionario funcionarioLogado;
    private Hospede hospedeLogado;

    public SistemaController(FuncionarioController funcionarioController,
                           HospedeController hospedeController,
                           QuartoController quartoController,
                           ReservaController reservaController) {
        this.funcionarioController = funcionarioController;
        this.hospedeController = hospedeController;
        this.quartoController = quartoController;
        this.reservaController = reservaController;
    }

    public void setFuncionarioLogado(Funcionario funcionario) {
        this.funcionarioLogado = funcionario;
        this.hospedeLogado = null; // Garante que apenas um tipo de usuário está logado
    }

    public void setHospedeLogado(Hospede hospede) {
        this.hospedeLogado = hospede;
        this.funcionarioLogado = null; // Garante que apenas um tipo de usuário está logado
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public Hospede getHospedeLogado() {
        return hospedeLogado;
    }

    public boolean isFuncionarioLogado() {
        return funcionarioLogado != null;
    }

    public boolean isHospedeLogado() {
        return hospedeLogado != null;
    }

    public FuncionarioController getFuncionarioController() {
        return funcionarioController;
    }

    public HospedeController getHospedeController() {
        return hospedeController;
    }

    public QuartoController getQuartoController() {
        return quartoController;
    }

    public ReservaController getReservaController() {
        return reservaController;
    }

    public void logout() {
        this.funcionarioLogado = null;
        this.hospedeLogado = null;
    }
}