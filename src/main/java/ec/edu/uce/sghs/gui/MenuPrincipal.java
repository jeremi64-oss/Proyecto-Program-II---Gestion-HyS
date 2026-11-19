package ec.edu.uce.sghs.gui;
import ec.edu.uce.sghs.dominio.*;
import ec.edu.uce.sghs.util.Validador;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class MenuPrincipal {

    private static final Scanner scanner = new Scanner(System.in);
    private static GestorSeguimiento gestor;
    private static Usuario usuarioActual;

    public static void iniciarSistema() {
        // Inicializamos el gestor y un catálogo por defecto
        CatalogoHabitos catalogo = new CatalogoHabitos(1L, "Bienestar Estudiantil FICA", "v1.0");
        catalogo.agregarPlantilla(new HabitoEstudio(101L, "Estudio POO Java",
                Habito.PolaridadHabito.POSITIVO, 3, 10, true, "Programación", 60, 15));
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
        System.out.println("  SISTEMA DE GESTIÓN DE HABITOS Y SUENO (SGHS)    ");
        System.out.println("  Universidad Central del Ecuador - FICA          ");
        System.out.println("==================================================");

        while (!salir) {
            if (usuarioActual == null) {
                salir = mostrarMenuLogin();
            } else {
                mostrarMenuPrincipalUsuario();
            }
        }
        System.out.println("\n¡Gracias por usar el sistema SGHS! ¡Hasta pronto companero!");
    }

    private static boolean mostrarMenuLogin() {
        System.out.println("\n--- MENU DE ACCESO ---");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrar Nuevo Usuario");
        System.out.println("3. Salir del Sistema");
        System.out.print("Seleccione una opcion: ");

        String opcionStr = scanner.nextLine();

        if (!Validador.validarEntero(opcionStr)) {
            System.out.println(" [ERROR] Ingrese un número valido.");
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
                System.out.println("[ERROR] Opcion no valida. Intente de nuevo.");
        }
        return false;
    }

    private static void autenticarUsuario() {
        System.out.print("Ingrese su correo institucional (@uce.edu.ec): ");
        String correo = scanner.nextLine();
        if (!Validador.validarCorreo(correo)) {
            System.out.println(" Formato de correo institucional invalido.");
            return;
        }

        System.out.print("Ingrese su contrasena (minimo 8 caracteres, letras y numeros): ");
        String password = scanner.nextLine();
        if (!Validador.validarPassword(password)) {
            System.out.println("[ERROR] Contrasena invalida segun los requisitos de seguridad.");
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
            System.out.println("[ERROR] Correo no valido.");
            return;
        }

        System.out.print("Ingrese contrasena segura: ");
        String pass = scanner.nextLine();
        if (!Validador.validarPassword(pass)) {
            System.out.println("[ERROR] Contrasena no cumple los requisitos.");
            return;
        }

        Usuario nuevo = new Usuario(gestor.siguienteIdUsuario(), nombre, correo, pass, true);
        nuevo.setRol(new Rol(2L, Rol.TipoRol.ESTUDIANTE, "Estudiante UCE"));
        if (gestor.agregarUsuario(nuevo)) {
            System.out.println(" ¡Usuario registrado exitosamente!");
        } else {
            System.out.println("[ERROR] El correo ya existe o se alcanzó la capacidad de usuarios.");
        }
    }

    private static void mostrarMenuPrincipalUsuario() {
        System.out.println("\n--- MENU PRINCIPAL [" + usuarioActual.getNombre() + "] ---");
        System.out.println("1. Ver Catalogo de Habitos y Plantillas");
        System.out.println("2. Registrar Registro de Sueno");
        System.out.println("3. Gestionar cronograma semanal");
        System.out.println("4. Generar Reporte de Progreso");
        System.out.println("5. Cerrar Sesion");
        if (esAdministrador()) {
            System.out.println("6. Gestionar cuentas de usuario");
        }
        System.out.print("Seleccione una opcion: ");

        String opcionStr = scanner.nextLine();
        if (!Validador.validarEntero(opcionStr)) {
            System.out.println("[ERROR] Opcion invalida.");
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
                System.out.println(" Sesion cerrada correctamente :) ");
                break;
            case 6:
                if (esAdministrador()) {
                    gestionarUsuariosMenu();
                } else {
                    System.out.println("Opción disponible para administradores.");
                }
                break;
            default:
                System.out.println("[ERROR] Opcion fuera de rango.");
        }
    }


    private static void explorarCatalogo() {
        CatalogoHabitos catalogo = gestor.getCatalogo();
        while (true) {
            System.out.println("\n--- CATÁLOGO: " + catalogo.getCategoria() + " ---");
            System.out.println("1. Listar  2. Crear  3. Consultar  4. Editar  5. Eliminar  0. Volver");
            try {
                switch (scanner.nextLine().strip()) {
                    case "1" -> {
                        if (catalogo.getPlantillas().isEmpty()) {
                            System.out.println("No hay plantillas registradas.");
                        }
                        catalogo.getPlantillas().forEach(System.out::println);
                    }
                    case "2" -> {
                        long id = pedirId("ID del hábito: ");
                        String nombre = pedirNombre();
                        Habito.PolaridadHabito polaridad = pedirPolaridad();
                        boolean sueno = pedirConfirmacion("¿Relacionado con el sueño?");
                        catalogo.agregarPlantilla(new Habito(id, nombre, polaridad, 0, 0, true), sueno);
                        System.out.println("Plantilla creada.");
                    }
                    case "3" -> System.out.println(catalogo.obtenerPlantilla(pedirId("ID del hábito: ")));
                    case "4" -> {
                        Habito habito = catalogo.obtenerPlantilla(pedirId("ID del hábito: "));
                        String nombre = pedirNombre();
                        Habito.PolaridadHabito polaridad = pedirPolaridad();
                        boolean sueno = pedirConfirmacion("¿Relacionado con el sueño?");
                        habito.editarHabito(nombre, polaridad);
                        catalogo.editarPlantilla(habito, sueno);
                        System.out.println("Plantilla editada.");
                    }
                    case "5" -> {
                        catalogo.eliminarPlantilla(pedirId("ID del hábito: "));
                        System.out.println("Plantilla eliminada.");
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("[ERROR] " + ex.getMessage());
            }
        }
    }

    private static void gestionarUsuariosMenu() {
        while (true) {
            System.out.println("\n--- CUENTAS DE USUARIO ---");
            System.out.println("1. Listar  2. Crear  3. Consultar  4. Editar  5. Eliminar  0. Volver");
            String opcion = scanner.nextLine().strip();
            try {
                switch (opcion) {
                    case "1" -> {
                        for (Usuario usuario : gestor.getUsuarios()) {
                            if (usuario != null) {
                                System.out.println(usuario);
                            }
                        }
                    }
                    case "2" -> registrarUsuarioNuevo();
                    case "3", "4", "5" -> gestionarCuentaMenu(opcion);
                    case "0" -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("[ERROR] " + ex.getMessage());
            }
        }
    }

    private static void gestionarCuentaMenu(String opcion) {
        long id = pedirId("ID del usuario: ");
        Usuario usuario = gestor.consultarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (opcion.equals("3")) {
            System.out.println(usuario);
            return;
        }
        if (opcion.equals("5")) {
            if (usuario == usuarioActual) {
                System.out.println("Puedes eliminar otra cuenta desde esta sesión.");
            } else {
                gestor.eliminarUsuario(id);
                System.out.println("Usuario eliminado.");
            }
            return;
        }
        String nombre = pedirNombre();
        if (!Validador.validarTextoGeneral(nombre)) {
            throw new IllegalArgumentException("El nombre solo debe contener letras y espacios.");
        }
        System.out.print("Correo institucional: ");
        String correo = scanner.nextLine().strip();
        if (!Validador.validarCorreo(correo)) {
            throw new IllegalArgumentException("Correo institucional no válido.");
        }
        boolean activo = usuario == usuarioActual || pedirConfirmacion("¿Cuenta activa?");
        Usuario datos = new Usuario(id, nombre, correo, usuario.getPasswordHash(), activo);
        System.out.println(gestor.editarUsuario(datos) ? "Usuario editado." : "El correo ya está registrado.");
    }

    private static long pedirId(String mensaje) {
        System.out.print(mensaje);
        long id = Long.parseLong(scanner.nextLine().strip());
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo.");
        }
        return id;
    }

    private static String pedirNombre() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().strip();
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        return nombre;
    }

    private static Habito.PolaridadHabito pedirPolaridad() {
        System.out.print("Polaridad (POSITIVO/NEGATIVO): ");
        return switch (scanner.nextLine().strip().toUpperCase(Locale.ROOT)) {
            case "POSITIVO" -> Habito.PolaridadHabito.POSITIVO;
            case "NEGATIVO" -> Habito.PolaridadHabito.NEGATIVO;
            default -> throw new IllegalArgumentException("Ingresa POSITIVO o NEGATIVO.");
        };
    }

    private static boolean pedirConfirmacion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        return switch (scanner.nextLine().strip().toLowerCase(Locale.ROOT)) {
            case "s" -> true;
            case "n" -> false;
            default -> throw new IllegalArgumentException("Ingresa s o n.");
        };
    }

    private static boolean esAdministrador() {
        return usuarioActual.getRol() != null
                && usuarioActual.getRol().getTipo() == Rol.TipoRol.ADMINISTRADOR;
    }

    private static void registrarSuenoMenu() {
        System.out.println("\n--- REGISTRO DE SUENO ---");
        LocalDate hoy = LocalDate.now();
        LocalTime dormir = LocalTime.of(22, 0);
        LocalTime despertar = LocalTime.of(6, 0);

        if (gestor.registrarSueno(usuarioActual, hoy, dormir, despertar)) {
            System.out.println(" Sueno registrado con exito para la fecha: " + hoy);
        } else {
            System.out.println("[ERROR] Error al registrar el sueno.");
        }
    }


    private static void generarCronogramaMenu() {
        CronogramaSemanal cronograma = gestor.generarPlanSeguimiento(usuarioActual, new Habito[0]);
        while (true) {
            System.out.println("\n--- CRONOGRAMA [SEMANA DEL " + cronograma.getFechaInicioSemana() + "] ---");
            System.out.println("1. Listar  2. Crear  3. Consultar  4. Editar  5. Eliminar  0. Volver");
            String opcion = scanner.nextLine().strip();
            try {
                switch (opcion) {
                    case "1" -> System.out.println(cronograma);
                    case "2" -> registrarActividad(cronograma, null);
                    case "3", "4", "5" -> {
                        long id = pedirId("ID de la actividad: ");
                        PlanItem item = cronograma.consultarPlanItem(id);
                        if (item == null) {
                            System.out.println("Actividad no encontrada.");
                        } else if (opcion.equals("3")) {
                            System.out.println(item);
                        } else if (opcion.equals("4")) {
                            registrarActividad(cronograma, item);
                        } else if (cronograma.eliminarPlanItem(id)) {
                            System.out.println("Actividad eliminada.");
                        }
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException | DateTimeException ex) {
                System.out.println("[ERROR] " + ex.getMessage());
            }
        }
    }

    private static void registrarActividad(CronogramaSemanal cronograma, PlanItem item) {
        System.out.print("Día de la semana: ");
        String dia = scanner.nextLine();
        System.out.print("Hora de inicio (HH:mm): ");
        LocalTime inicio = LocalTime.parse(scanner.nextLine().strip());
        System.out.print("Hora de fin (HH:mm): ");
        LocalTime fin = LocalTime.parse(scanner.nextLine().strip());
        System.out.print("ID del hábito (Enter para dejarlo sin hábito): ");
        String idHabito = scanner.nextLine().strip();
        Habito habito = idHabito.isEmpty() ? null : gestor.getCatalogo().obtenerPlantilla(Long.parseLong(idHabito));

        if (item == null) {
            System.out.println(cronograma.agregarPlanItem(dia, inicio, fin, habito)
                    ? "Actividad creada." : "Cronograma lleno o actividad solapada.");
            return;
        }
        item.reprogramar(dia, inicio, fin);
        item.setHabito(habito);
        item.setEstado(pedirConfirmacion("¿Actividad completada?") ? "COMPLETADO" : "PENDIENTE");
        System.out.println(cronograma.editarPlanItem(item) ? "Actividad editada." : "La actividad se solapa con otra.");
    }

    private static void verReporteProgreso() {
        System.out.println("\n--- REPORTE DE PROGRESO ---");
        LocalDate inicio = LocalDate.now().minusDays(7);
        LocalDate fin = LocalDate.now();
        String reporte = gestor.generarReporteProgreso(usuarioActual, inicio, fin);
        System.out.println(reporte);
    }
}
