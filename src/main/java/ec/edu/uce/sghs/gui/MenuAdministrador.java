package ec.edu.uce.sghs.gui;

import java.util.Scanner;

public class MenuAdministrador {

    public static void mostrarMenu(Scanner scanner) {
        int opcion = 0;

        do {
            System.out.println("\n==============================================");
            System.out.println("           MENÚ GESTIÓN DE USUARIOS           ");
            System.out.println("==============================================");
            System.out.println("1. Listar cuentas de usuario ");
            System.out.println("2. Asignar rol de acceso ");
            System.out.println("3. Activar o desactivar cuenta ");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("[Opción: Listar cuentas de usuario seleccionado]");
                        break;
                    case 2:
                        System.out.println("[Opción: Asignar rol de acceso seleccionado]");
                        break;
                    case 3:
                        System.out.println("[Opción: Activar/Desactivar cuenta seleccionado]");
                        break;
                    case 4:
                        System.out.println("Cerrando sesión del administrador...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Debe ingresar un número entero.");
                scanner.nextLine();
            }
        } while (opcion != 4);
    }
}
