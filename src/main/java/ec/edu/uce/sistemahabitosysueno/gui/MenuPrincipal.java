package ec.edu.uce.sistemahabitosysueno.gui;

import ec.edu.uce.sistemahabitosysueno.util.Validador;
import java.util.Scanner;

    public class MenuPrincipal {

        public static void mostrarMenu() {
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            do {
                System.out.println("\n==============================================");
                System.out.println("      SISTEMA DE GESTIÓN DE HÁBITOS Y SUEÑO");
                System.out.println("==========================++====================");
                System.out.println("1. Iniciar Sesión ");
                System.out.println("2. Registrarse ");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");

                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1:
                            System.out.println("\n--- INICIAR SESIÓN ---");
                            String correo = "";
                            while (true) {
                                System.out.print("Ingrese correo: ");
                                correo = scanner.nextLine();
                                if (Validador.validarCorreo(correo)) {
                                    break; // Sale del bucle de correo si es correcto
                                }
                                System.out.println("[ERROR] Formato inválido. Ejemplo: usuario@uce.edu.ec. Intente de nuevo.");
                            }

                            String contrasena = "";
                            while (true) {
                                System.out.print("Ingrese contraseña (mínimo 8 caracteres): ");
                                contrasena = scanner.nextLine();
                                if (Validador.validarContrasena(contrasena)) {
                                    break; // Sale del bucle de contraseña si es correcta
                                }
                                System.out.println("[ERROR] Contraseña demasiado corta. Intente de nuevo.");
                            }

                            // Simulador de redirección según el rol ingresado
                            System.out.println("\nSeleccione el rol con el que desea ingresar para probar:");
                            System.out.println("1. Estudiante");
                            System.out.println("2. Administrador");
                            System.out.print("Opción: ");
                            int rol = scanner.nextInt();
                            scanner.nextLine();

                            if (rol == 1) {
                                MenuEstudiante.mostrarMenu(scanner);
                            } else if (rol == 2) {
                                MenuAdministrador.mostrarMenu(scanner);
                            } else {
                                System.out.println("Rol no válido.");
                            }
                            break;

                        case 2:
                            System.out.println("\n--- REGISTRARSE ---");
                            String correoReg = "";
                            while (true) {
                                System.out.print("Ingrese correo: ");
                                correoReg = scanner.nextLine();
                                if (Validador.validarCorreo(correoReg)) {
                                    break;
                                }
                                System.out.println("[ERROR] Formato de correo inválido. Intente de nuevo.");
                            }

                            String passReg = "";
                            while (true) {
                                System.out.print("Ingrese contraseña: ");
                                passReg = scanner.nextLine();
                                if (Validador.validarContrasena(passReg)) {
                                    break;
                                }
                                System.out.println("[ERROR] La contraseña debe tener al menos 8 caracteres. Intente de nuevo.");
                            }

                            System.out.println("[ÉXITO] ¡Registro simulado exitoso!");
                            break;

                        case 3:
                            System.out.println("Saliendo del sistema...");
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }
                } else {
                    System.out.println("Debe ingresar un número entero.");
                    scanner.nextLine();
                }
            } while (opcion != 3);
        }
    }
