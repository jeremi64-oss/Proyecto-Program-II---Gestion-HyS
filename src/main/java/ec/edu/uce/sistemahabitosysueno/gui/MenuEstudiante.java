package ec.edu.uce.sistemahabitosysueno.gui;

import java.util.Scanner;

public class MenuEstudiante {

    public static void mostrarMenu(Scanner scanner) {
        int opcion = 0;

        do {
            System.out.println("\n==============================================");
            System.out.println("           MENÚ PRINCIPAL ESTUDIANTE          ");
            System.out.println("==============================================");
            System.out.println("1. Gestionar hábitos semanales ");
            System.out.println("2. Registrar un diario de sueño");
            System.out.println("3. Establecer metas ");
            System.out.println("4. Controlar la constancia de hábitos ");
            System.out.println("5. Visualizar el horario semanal ");
            System.out.println("6. Analizar y reportar el progreso ");
            System.out.println("7. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        menuGestionarHabitos(scanner);
                        break;
                    case 2:
                        menuDiarioSueno(scanner);
                        break;
                    case 3:
                        menuEstablecerMetas(scanner);
                        break;
                    case 4:
                        menuControlarConstancia(scanner);
                        break;
                    case 5:
                        menuVisualizarHorario(scanner);
                        break;
                    case 6:
                        menuAnalizarReportar(scanner);
                        break;
                    case 7:
                        System.out.println("Cerrando sesión del estudiante...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Debe ingresar un número entero.");
                scanner.nextLine();
            }
        } while (opcion != 7);
    }

    // --- SUBMENÚS ---

    private static void menuGestionarHabitos(Scanner scanner) {
        int subOpcion = 0;
        do {
            System.out.println("\n--- GESTIONAR HÁBITOS SEMANALES ---");
            System.out.println("1. Crear hábito ");
            System.out.println("2. Editar hábito ");
            System.out.println("3. Eliminar hábito ");
            System.out.println("4. Volver al menú estudiante");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                subOpcion = scanner.nextInt();
                scanner.nextLine();
                switch (subOpcion) {
                    case 1:
                        System.out.println("[Opción: Crear hábito seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Editar hábito seleccionado]");
                        break;
                    case 3:
                        System.out.println("[Opción: Eliminar hábito seleccionado]");
                        break;
                    case 4:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.nextLine();
            }
        } while (subOpcion != 4);
    }

    private static void menuDiarioSueno(Scanner scanner) {
        int subOpcion = 0;
        do {
            System.out.println("\n--- REGISTRAR DIARIO DE SUEÑO ---");
            System.out.println("1. Registrar sueño diario ");
            System.out.println("2. Consultar historial de sueño ");
            System.out.println("3. Volver al menú estudiante");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                subOpcion = scanner.nextInt();
                scanner.nextLine();
                switch (subOpcion) {
                    case 1:
                        System.out.println("[Opción: Registrar sueño diario seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Consultar historial de sueño seleccionado]");
                        break;
                    case 3:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.nextLine();
            }
        } while (subOpcion != 3);
    }

    private static void menuEstablecerMetas(Scanner scanner) {
        int subOpcion = 0;
        do {
            System.out.println("\n--- ESTABLECER METAS ---");
            System.out.println("1. Definir meta de horas de sueño ");
            System.out.println("2. Definir meta semanal de hábito ");
            System.out.println("3. Volver al menú estudiante");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                subOpcion = scanner.nextInt();
                scanner.nextLine();
                switch (subOpcion) {
                    case 1:
                        System.out.println("[Opción: Definir meta de horas de sueño seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Definir meta semanal de hábito seleccionado]");
                        break;
                    case 3:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.nextLine();
            }
        } while (subOpcion != 3);
    }

    private static void menuControlarConstancia(Scanner scanner) {
        int subOpcion = 0;
        do {
            System.out.println("\n--- CONTROLAR CONSTANCIA DE HÁBITOS ---");
            System.out.println("1. Marcar cumplimiento diario ");
            System.out.println("2. Consultar constancia semanal ");
            System.out.println("3. Volver al menú estudiante");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                subOpcion = scanner.nextInt();
                scanner.nextLine();
                switch (subOpcion) {
                    case 1:
                        System.out.println("[Opción: Marcar cumplimiento diario seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Consultar constancia semanal seleccionado]");
                        break;
                    case 3:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.nextLine();
            }
        } while (subOpcion != 3);
    }

    private static void menuVisualizarHorario(Scanner scanner) {
        int subOpcion = 0;
        do {
            System.out.println("\n--- VISUALIZAR HORARIO SEMANAL ---");
            System.out.println("1. Ver horario semanal");
            System.out.println("2. Escribir comentarios por fecha ");
            System.out.println("3. Volver al menú estudiante");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                subOpcion = scanner.nextInt();
                scanner.nextLine();
                switch (subOpcion) {
                    case 1:
                        System.out.println("[Opción: Ver horario semanal seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Escribir comentarios por fecha seleccionado]");
                        break;
                    case 3:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.nextLine();
            }
        } while (subOpcion != 3);
    }

    private static void menuAnalizarReportar(Scanner scanner) {
        int subOpcion = 0;
        do {
            System.out.println("\n--- ANALIZAR Y REPORTAR EL PROGRESO ---");
            System.out.println("1. Reportar cumplimiento y constancia ");
            System.out.println("2. Reportar evolución del sueño ");
            System.out.println("3. Correlacionar sueño y hábitos ");
            System.out.println("4. Volver al menú estudiante");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                subOpcion = scanner.nextInt();
                scanner.nextLine();
                switch (subOpcion) {
                    case 1:
                        System.out.println("[Opción: Reportar cumplimiento y constancia seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Reportar evolución del sueño seleccionado]");
                        break;
                    case 3:
                        System.out.println("[Opción: Correlacionar sueño y hábitos seleccionado]");
                        break;
                    case 4:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                scanner.nextLine();
            }
        } while (subOpcion != 4);
    }
}
