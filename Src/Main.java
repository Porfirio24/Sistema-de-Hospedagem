import data.Database;
import views.LoginView;

import java.util.Scanner;

import controllers.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializando o database...
            Database.carregarFuncionarios();
            Database.carregarHospedes();
            Database.carregarQuartos();
            Database.carregarReservas();

            // Inicializando controllers...
            FuncionarioController funcionarioController = new FuncionarioController();
            HospedeController hospedeController = new HospedeController();
            QuartoController quartoController = new QuartoController();
            ReservaController reservaController = new ReservaController();
            SistemaController sistemaController = new SistemaController(
                    funcionarioController, hospedeController, quartoController, reservaController);

            // Inicializando view...
            Scanner scanner = new Scanner(System.in);
            LoginView loginView = new LoginView(scanner, funcionarioController, 
                                             hospedeController, sistemaController);

            // Exibir menu de login
            loginView.exibirMenuLogin();

        } catch (Exception e) {
            System.err.println("Erro inesperado ao inicializar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}