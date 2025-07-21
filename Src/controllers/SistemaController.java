package controllers;

import models.Funcionario;

public class SistemaController {
    private final FuncionarioController funcionarioController;
    private final HospedeController hospedeController;
    private final QuartoController quartoController;
    private final ReservaController reservaController;
    
    private Funcionario funcionarioLogado;

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
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public boolean isFuncionarioLogado() {
        return funcionarioLogado != null;
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
    }
}