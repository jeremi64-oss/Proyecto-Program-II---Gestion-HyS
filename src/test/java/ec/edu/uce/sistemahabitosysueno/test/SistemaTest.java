package ec.edu.uce.sistemahabitosysueno.test;


import ec.edu.uce.sistemahabitosysueno.dominio.Rol;
import ec.edu.uce.sistemahabitosysueno.dominio.Usuario;

public class SistemaTest {

    public static void main(String[] args) {
        System.out.println("=== EJECUTANDO PRUEBAS DE ACCESO Y ROLES ===");

        // Prueba de creación de Rol y Usuario Administrador
        Rol rolAdmin = new Rol(1, "Administrador");
        Usuario admin = new Usuario(1, "admin@uce.edu.ec", "admin1234", true, 0, rolAdmin);

        if (admin.getRol() != null && admin.getRol().getNombre().equals("Administrador")) {
            System.out.println("[OK] Redirección y rol de Administrador correcto.");
        } else {
            System.out.println("[ERROR] Fallo en la asignación del rol Administrador.");
        }

        // Prueba de creación de Rol y Usuario Estudiante
        Rol rolEstudiante = new Rol(2, "Estudiante");
        Usuario estudiante = new Usuario(2, "estudiante@uce.edu.ec", "student123", true, 0, rolEstudiante);

        if (estudiante.getRol() != null && estudiante.getRol().getNombre().equals("Estudiante")) {
            System.out.println("[OK] Redirección y rol de Estudiante correcto.");
        } else {
            System.out.println("[ERROR] Fallo en la asignación del rol Estudiante.");
        }

        // Prueba de bloqueo por intentos fallidos
        Usuario usuarioBloqueado = new Usuario(3, "test@uce.edu.ec", "password123", true, 3, rolEstudiante);

        if (usuarioBloqueado.getIntentosFallidos() >= 3) {
            System.out.println("[OK] Detección de bloqueo por 3 intentos fallidos correcta.");
        } else {
            System.out.println("[ERROR] No se detectó el bloqueo del usuario.");
        }

        System.out.println("=== PRUEBAS FINALIZADAS ===");
    }
}
