package ec.edu.uce.sghs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    private static final String CORREO_REGEX = "^[\\w._%+-]+@uce\\.edu\\.ec$";

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private static final String TEXTO_DESCRIPCION_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

    public static boolean validarCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            return false;
        }
        Pattern pattern = Pattern.compile(CORREO_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(correo.strip());
        return matcher.matches();
    }

    public static boolean validarPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validarTextoGeneral(String texto) {
        if (texto == null || texto.isBlank()) {
            return false;
        }
        Pattern pattern = Pattern.compile(TEXTO_DESCRIPCION_REGEX);
        Matcher matcher = pattern.matcher(texto.strip());
        return matcher.matches();
    }

    public static boolean validarEntero(String texto) {
        if (texto == null || texto.isBlank()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^-?\\d+$");
        Matcher matcher = pattern.matcher(texto.strip());
        return matcher.matches();
    }
}