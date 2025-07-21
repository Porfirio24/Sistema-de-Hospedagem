package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate stringParaData(String dataStr) throws DateTimeParseException {
        return LocalDate.parse(dataStr, FORMATTER);
    }

    public static String dataParaString(LocalDate data) {
        return data.format(FORMATTER);
    }

    public static boolean validarData(String dataStr) {
        try {
            stringParaData(dataStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validarPeriodo(LocalDate entrada, LocalDate saida) {
        return !saida.isBefore(entrada) && !entrada.isBefore(LocalDate.now());
    }
}