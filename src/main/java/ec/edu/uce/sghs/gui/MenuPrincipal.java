package ec.edu.uce.sghs.gui;
import ec.edu.uce.sghs.dominio.*;
import ec.edu.uce.sghs.util.Validador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuPrincipal {

    private static final Scanner scanner = new Scanner(System.in);
    private static GestorSeguimiento gestor;
    private static Usuario usuarioActual;

    public static void iniciarSistema() {
        // Inicializamos el gestor y un catálogo por defecto
        CatalogoHabitos catalogo = new CatalogoHabitos(1L, "Bienestar Estudiantil FICA", "v1.0");
        gestor = new GestorSeguimiento(10, catalogo);

        // Creamos un usuario administrador y un estudiante por defecto para pruebas
        Rol rolAdmin = new Rol(1L, Rol.TipoRol.ADMINISTRADOR, "Administrador del Sistema");
        Rol rolEstudiante = new Rol(2L, Rol.TipoRol.ESTUDIANTE, "Estudiante UCE");

        Usuario admin = new Usuario(1L, "Admin UCE", "admin@uce.edu.ec", "admin1234", true);
        admin.setRol(rolAdmin);

        Usuario estudiante = new Usuario(2L, "Jaidrovo UCE", "jaidrovo@uce.edu.ec", "pass1234", true);
        estudiante.setRol(rolEstudiante);

        gestor.agregarUsuario(admin);
        gestor.agregarUsuario(estudiante);

        boolean salir = false;
        System.out.println("==================================================");
        System.out.println("  SISTEMA DE GESTIÓN DE HÁBITOS Y SUEÑO (SGHS)    ");
        System.out.println("  Universidad Central del Ecuador - FICA          ");
        System.out.println("==================================================");

        while (!salir) {
            if (usuarioActual == null) {
                salir = mostrarMenuLogin();
            } else {
                mostrarMenuPrincipalUsuario();
            }
        }
        System.out.println("\n¡Gracias por usar el sistema SGHS! ¡Hasta pronto compañero!");
    }

    private static boolean mostrarMenuLogin() {
        System.out.println("\n--- MENÚ DE ACCESO ---");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrar Nuevo Usuario");
        System.out.println("3. Salir del Sistema");
        System.out.print("Seleccione una opción: ");

        String opcionStr = scanner.nextLine();

        if (!Validador.validarEntero(opcionStr)) {
            System.out.println(" [ERROR] Ingrese un número válido.");
            return false;
        }

        int opcion = Integer.parseInt(opcionStr.strip());

        switch (opcion) {
            case 1:
                autenticarUsuario();
                break;
            case 2:
                registrarUsuarioNuevo();
                break;
            case 3:
                return true;
            default:
                System.out.println("[ERROR] Opción no válida. Intente de nuevo.");
        }
        return false;
    }

    private static void autenticarUsuario() {
        System.out.print("Ingrese su correo institucional (@uce.edu.ec): ");
        String correo = scanner.nextLine();
        if (!Validador.validarCorreo(correo)) {
            System.out.println(" Formato de correo institucional inválido.");
            return;
        }

        System.out.print("Ingrese su contraseña (mínimo 8 caracteres, letras y números): ");
        String password = scanner.nextLine();
        if (!Validador.validarPassword(password)) {
            System.out.println("[ERROR] Contraseña inválida según los requisitos de seguridad.");
            return;
        }

        // Buscar usuario en el gestor
        for (Usuario u : gestor.getUsuarios()) {
            if (u != null && u.autenticar(correo, password)) {
                usuarioActual = u;
                System.out.println(" ¡Bienvenido(a), " + u.getNombre() + "!");
                return;
            }
        }
        System.out.println("[ERROR] Credenciales incorrectas o cuenta inactiva.");
    }

    private static void registrarUsuarioNuevo() {
        System.out.print("Ingrese su Nombre completo: ");
        String nombre = scanner.nextLine();
        if (!Validador.validarTextoGeneral(nombre)) {
            System.out.println("[ERROR] El nombre solo debe contener letras y espacios.");
            return;
        }

        System.out.print("Ingrese correo institucional (@uce.edu.ec): ");
        String correo = scanner.nextLine();
        if (!Validador.validarCorreo(correo)) {
            System.out.println("[ERROR] Correo no válido.");
            return;
        }

        System.out.print("Ingrese contraseña segura: ");
        String pass = scanner.nextLine();
        if (!Validador.validarPassword(pass)) {
            System.out.println("[ERROR] Contraseña no cumple los requisitos.");
            return;
        }

        Usuario nuevo = new Usuario((long) (gestor.getUsuarios().length + 1), nombre, correo, pass, true);
        if (gestor.agregarUsuario(nuevo)) {
            System.out.println(" ¡Usuario registrado exitosamente!");
        } else {
            System.out.println("[ERROR] Error: Capacidad máxima de usuarios alcanzada.");
        }
    }

    private static void mostrarMenuPrincipalUsuario() {
        System.out.println("\n--- MENÚ PRINCIPAL [" + usuarioActual.getNombre() + "] ---");
        System.out.println("1. Ver Catálogo de Hábitos y Plantillas");
        System.out.println("2. Registrar Registro de Sueño");
        System.out.println("3. Generar Plan de Seguimiento y Cronograma");
        System.out.println("4. Generar Reporte de Progreso");
        System.out.println("5. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");

        String opcionStr = scanner.nextLine();
        if (!Validador.validarEntero(opcionStr)) {
            System.out.println("[ERROR] Opción inválida.");
            return;
        }

        int opcion = Integer.parseInt(opcionStr.strip());

        switch (opcion) {
            case 1:
                explorarCatalogo();
                break;
            case 2:
                registrarSuenoMenu();
                break;
            case 3:
                generarCronogramaMenu();
                break;
            case 4:
                verReporteProgreso();
                break;
            case 5:
                usuarioActual = null;
                System.out.println(" Sesión cerrada correctamente :) ");
                break;
            default:
                System.out.println("[ERROR] Opción fuera de rango.");
        }
    }

    private static void explorarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE HÁBITOS: " + gestor.getCatalogo().getCategoria() + " ---");
        System.out.println("Versión: " + gestor.getCatalogo().getVersion());
        System.out.println("Plantillas registradas: " + gestor.getCatalogo().getPlantillas().size());

        // Agregamos un hábito de prueba si está vacío para ilustrar
        if (gestor.getCatalogo().getPlantillas().isEmpty()) {
            HabitoEstudio habitoEstudio = new HabitoEstudio(101L, "Estudio POO Java", Habito.PolaridadHabito.POSITIVO, 3, 10, true, "Programación", 60, 15);
            gestor.getCatalogo().agregarPlantilla(habitoEstudio, false);
            System.out.println(" Se agregó la plantilla predeterminada: " + habitoEstudio.getNombre());
        }

        for (Habito h : gestor.getCatalogo().getPlantillas()) {
            System.out.println(" - [" + h.getIdHabito() + "] " + h.getNombre() + " (Polaridad: " + h.getPolaridad() + ")");
        }
    }

    private static void registrarSuenoMenu() {
        System.out.println("\n--- REGISTRO DE SUEÑO ---");
        LocalDate hoy = LocalDate.now();
        LocalTime dormir = LocalTime.of(22, 0);
        LocalTime despertar = LocalTime.of(6, 0);

        if (gestor.registrarSueno(usuarioActual, hoy, dormir, despertar)) {
            System.out.println(" Sueño registrado con éxito para la fecha: " + hoy);
        } else {
            System.out.println("[ERROR] Error al registrar el sueño.");
        }
    }

    private static void generarCronogramaMenu() {
        System.out.println("\n--- GENERACIÓN DE CRONOGRAMA SEMANAL ---");
        Habito[] habitosArr = new Habito[0];
        CronogramaSemanal crono = gestor.generarPlanSeguimiento(usuarioActual, habitosArr);
        if (crono != null) {
            System.out.println("[ERROR] Cronograma generado con ID: " + crono.getIdCronograma() + " para la semana del: " + crono.getFechaInicioSemana());
        }
    }

    private static void verReporteProgreso() {
        System.out.println("\n--- REPORTE DE PROGRESO ---");
        LocalDate inicio = LocalDate.now().minusDays(7);
        LocalDate fin = LocalDate.now();
        String reporte = gestor.generarReporteProgreso(usuarioActual, inicio, fin);
        System.out.println(reporte);
    }
}
