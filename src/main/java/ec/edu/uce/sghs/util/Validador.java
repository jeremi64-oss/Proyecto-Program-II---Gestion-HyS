package ec.edu.uce.sghs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    // Valida correo electrónico
    public static boolean validarCorreo(String correo) {
        // Expresión regular básica para correos (texto@dominio.extensión)
        String regexCorreo = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(regexCorreo);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    // Valida contraseña (mínimo 8 caracteres)
    public static boolean validarContrasena(String contrasena) {
        if (contrasena == null) {
            return false;
        }
        // Valida que tenga al menos 8 caracteres
        String regexPass = "^.{8,}$";
        Pattern pattern = Pattern.compile(regexPass);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    // Valida horas de sueño (número entero entre 4 y 12)
    // Primero que sea estrictamente un número entero

    public static boolean validarHorasSueno(String entradaHoras) {
        String regexNumero = "^\\d+$";
        Pattern pattern = Pattern.compile(regexNumero);
        Matcher matcher = pattern.matcher(entradaHoras);

        if (matcher.matches()) {
            int horas = Integer.parseInt(entradaHoras);
            return horas >= 4 && horas <= 12;
        }
        return false;
    }
}
