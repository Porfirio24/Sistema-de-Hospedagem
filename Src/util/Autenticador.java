package util;

public class Autenticador {
    public static String criptografarSenha(String senha) {
        // Implementação básica de criptografia (em produção, usar algo mais seguro como BCrypt)
        return Integer.toString(senha.hashCode());
    }

    public static boolean validarSenha(String senhaDigitada, String senhaArmazenada) {
        return criptografarSenha(senhaDigitada).equals(senhaArmazenada);
    }
}