import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el nombre de su empresa: ");
        String nombreEmpresa = sc.nextLine();
        int añoFiscal = obtenerAñoFiscal(sc);

        List<Cuenta> cuentas = new ArrayList<>();

        while (true) {
            int usuarioInput = menuPrincipal(nombreEmpresa, sc);

            switch (usuarioInput) {
                case 1:
                    int userInput2 = menuCuentas(sc, cuentas);
                    if (userInput2 == 1) {
                        agregarCuenta(sc, cuentas);
                    } else if (userInput2 == 2) {
                        eliminarCuenta(sc, cuentas);
                    } else if (userInput2 == 3) {
                        // Volver al menú principal
                    }
                    break;

                case 2:
                    menuEstadosFinancieros(sc, nombreEmpresa, añoFiscal, cuentas);
                    break;

                case 3:
                    // Salir
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static int menuPrincipal(String nombreEmpresa, Scanner sc) {
        System.out.println("-----------------------------------------------------");
        System.out.println("                   SISTEMA CONTABLE");
        System.out.println("-----------------------------------------------------");
        System.out.println("                Empresa " + nombreEmpresa);
        System.out.println("Menu del sistema:");
        System.out.println("1) Cuentas.");
        System.out.println("2) Ver estados financieros.");
        System.out.println("3) Salir del sistema.");
        System.out.print("Digite la opción solicitada (Número 1, 2 o 3): ");

        try {
            int usuarioInput = sc.nextInt();
            sc.nextLine();
            if (usuarioInput >= 1 && usuarioInput <= 3) {
                return usuarioInput;
            } else {
                System.out.println("Digite una opción válida");
            }
        } catch (InputMismatchException e) {
            System.out.print("Digite el número específico de la opción dada por el menú: ");
            sc.nextLine();
        }

        return menuPrincipal(nombreEmpresa, sc);
    }

    public static int menuCuentas(Scanner sc, List<Cuenta> cuentas) {
        int contador = cuentas.size();
        System.out.println("------------------------------------------------");
        System.out.println("                    CUENTAS");
        System.out.println("Cuentas añadidas: " + contador);

        if (contador == 0) {
            System.out.println("No hay cuentas para mostrar.");
        } else {
            for (int i = 0; i < cuentas.size(); i++) {
                System.out.println((i + 1) + " - " + cuentas.get(i).imprimir());
            }
        }

        System.out.println("1) Añadir cuentas.");
        if (contador > 0) {
            System.out.println("2) Eliminar cuentas.");
        }
        System.out.println("3) Salir.");
        System.out.print("Opción a elegir (1, 2 o 3): ");

        try {
            int userInput2 = sc.nextInt();
            sc.nextLine();
            if (userInput2 >= 1 && userInput2 <= 3) {
                return userInput2;
            } else {
                System.out.println("Número no válido. Por favor, escoja un número válido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Digite un número válido dentro del campo de opciones.");
            sc.nextLine();
        }

        return menuCuentas(sc, cuentas);
    }

    public static void agregarCuenta(Scanner sc, List<Cuenta> cuentas) {
        System.out.println("Elige el tipo de cuenta que desea agregar: ");
        System.out.println("1) Activo");
        System.out.println("2) Pasivo");
        System.out.println("3) Capital");
        System.out.println("4) Ingreso");
        System.out.println("5) Gasto");
        System.out.print("Digite la opción a elegir: ");

        while (true) {
            try {
                int opcionAgregarCuenta = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if (opcionAgregarCuenta >= 1 && opcionAgregarCuenta <= 5) {
                    switch (opcionAgregarCuenta) {
                        case 1:
                            agregarActivo(sc, cuentas);
                            break;
                        case 2:
                            agregarPasivo(sc, cuentas);
                            break;
                        case 3:
                            agregarCapital(sc, cuentas);
                            break;
                        case 4:
                            agregarIngreso(sc, cuentas);
                            break;
                        case 5:
                            agregarGasto(sc, cuentas);
                            break;
                    }

                    break; // Salir del bucle después de agregar la cuenta
                } else {
                    System.out.println("Elige un número del 1 al 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida.");
                sc.nextLine(); // Consumir el salto de línea
            }
        }
    }

    public static void agregarActivo(Scanner sc, List<Cuenta> cuentas) {
        System.out.println("Elige el tipo de activo que desea agregar:");
        System.out.println("1) Activo Circulante");
        System.out.println("2) Activo No Circulante");
        System.out.print("Digite la opción a elegir: ");

        while (true) {
            try {
                int opcionActivo = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if (opcionActivo == 1 || opcionActivo == 2) {
                    System.out.print("Digite el nombre del activo: ");
                    String nombreDeLaCuenta = sc.nextLine();

                    System.out.print("Digite el monto del activo: ");
                    double montoDeLaCuenta = sc.nextDouble();
                    sc.nextLine(); // Consumir el salto de línea

                    String tipoDeActivo = (opcionActivo == 1) ? "Activo Circulante" : "Activo No Circulante";
                    Cuenta activo = new Activos(nombreDeLaCuenta, tipoDeActivo, montoDeLaCuenta);
                    cuentas.add(activo);
                    break; // Salir del bucle después de agregar la cuenta
                } else {
                    System.out.println("Elige una opción válida (1 o 2).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida.");
                sc.nextLine(); // Consumir el salto de línea
            }
        }
    }

    public static void agregarPasivo(Scanner sc, List<Cuenta> cuentas) {
        System.out.println("Elige el tipo de pasivo que desea agregar:");
        System.out.println("1) Pasivo Circulante");
        System.out.println("2) Pasivo No Circulante");
        System.out.print("Digite la opción a elegir: ");

        while (true) {
            try {
                int opcionPasivo = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if (opcionPasivo == 1 || opcionPasivo == 2) {
                    System.out.print("Digite el nombre del pasivo: ");
                    String nombreDeLaCuenta = sc.nextLine();

                    System.out.print("Digite el monto del pasivo: ");
                    double montoDeLaCuenta = sc.nextDouble();
                    sc.nextLine(); // Consumir el salto de línea

                    String tipoDePasivo = (opcionPasivo == 1) ? "Pasivo Circulante" : "Pasivo No Circulante";
                    Cuenta pasivo = new Pasivos(nombreDeLaCuenta, tipoDePasivo, montoDeLaCuenta);
                    cuentas.add(pasivo);
                    break; // Salir del bucle después de agregar la cuenta
                } else {
                    System.out.println("Elige una opción válida (1 o 2).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida.");
                sc.nextLine(); // Consumir el salto de línea
            }
        }
    }

    public static void agregarCapital(Scanner sc, List<Cuenta> cuentas) {
        System.out.println("Elige el tipo de capital que desea agregar:");
        System.out.println("1) Capital Ganado");
        System.out.println("2) Capital Contribuido");
        System.out.print("Digite la opción a elegir: ");

        while (true) {
            try {
                int opcionCapital = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if (opcionCapital == 1 || opcionCapital == 2) {
                    System.out.print("Digite el nombre del capital: ");
                    String nombreDeLaCuenta = sc.nextLine();

                    System.out.print("Digite el monto del capital: ");
                    double montoDeLaCuenta = sc.nextDouble();
                    sc.nextLine(); // Consumir el salto de línea

                    String tipoDeCapital = (opcionCapital == 1) ? "Capital Ganado" : "Capital Contribuido";
                    Cuenta capital = new Capital(nombreDeLaCuenta, tipoDeCapital, montoDeLaCuenta);
                    cuentas.add(capital);
                    break; // Salir del bucle después de agregar la cuenta
                } else {
                    System.out.println("Elige una opción válida (1 o 2).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida.");
                sc.nextLine(); // Consumir el salto de línea
            }
        }
    }

    public static void agregarIngreso(Scanner sc, List<Cuenta> cuentas) {
        System.out.print("Digite el nombre del ingreso: ");
        String nombreDeLaCuenta = sc.nextLine();

        System.out.print("Digite el monto del ingreso: ");
        double montoDeLaCuenta = sc.nextDouble();
        sc.nextLine(); // Consumir el salto de línea

        Cuenta ingreso = new Ingresos(nombreDeLaCuenta, "Ingresos", montoDeLaCuenta);
        cuentas.add(ingreso);
    }

    public static void agregarGasto(Scanner sc, List<Cuenta> cuentas) {
        System.out.print("Digite el nombre del gasto: ");
        String nombreDeLaCuenta = sc.nextLine();

        System.out.print("Digite el monto del gasto: ");
        double montoDeLaCuenta = sc.nextDouble();
        sc.nextLine(); // Consumir el salto de línea

        Cuenta gasto = new Gastos(nombreDeLaCuenta, "Gastos", montoDeLaCuenta);
        cuentas.add(gasto);
    }

    public static void eliminarCuenta(Scanner sc, List<Cuenta> cuentas) {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas para eliminar.");
            return;
        }

        System.out.print("Digite el número de la cuenta a eliminar: ");
        int numeroCuenta = 0;
        while (true) {
            try {
                numeroCuenta = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea
                if (numeroCuenta >= 1 && numeroCuenta <= cuentas.size()) {
                    cuentas.remove(numeroCuenta - 1);
                    System.out.println("Cuenta eliminada satisfactoriamente.");
                    break;
                } else {
                    System.out.println("Digite un número de cuenta válido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite un número de cuenta válido.");
                sc.nextLine(); // Consumir el salto de línea
            }
        }
    }

    public static int obtenerAñoFiscal(Scanner sc) {
        while (true) {
            try {
                System.out.print("Digite el año fiscal actual: ");
                int añoFiscal = sc.nextInt();
                if (añoFiscal >= 1900 && añoFiscal <= 3000) {
                    return añoFiscal;
                } else {
                    System.out.println("Año no válido. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe digitar un número.");
                sc.nextLine();
            }
        }
    }

    public static void menuEstadosFinancieros(Scanner sc, String nombreDeLaEmpresa, int añoFiscal,
            List<Cuenta> cuentas) {
        System.out.println("-----------------------------------------------------");
        System.out.println("           MENÚ DE ESTADOS FINANCIEROS");
        System.out.println("-----------------------------------------------------");
        System.out.println("                Empresa " + nombreDeLaEmpresa);
        System.out.println("1) Ver estado de resultados.");
        System.out.println("2) Ver balance general.");
        System.out.print("Digite la opción solicitada (Número 1 o 2): ");

        try {
            int usuarioInput = sc.nextInt();
            sc.nextLine();
            switch (usuarioInput) {
                case 1:
                    estadoDeResultados(nombreDeLaEmpresa, añoFiscal, cuentas);
                    break;

                case 2:
                    balanceGeneral(nombreDeLaEmpresa, añoFiscal, cuentas);
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } catch (InputMismatchException e) {
            System.out.print("Digite el número específico de la opción dada por el menú: ");
            sc.nextLine();
        }
    }

    public static void estadoDeResultados(String nombreDeLaEmpresa, int añoFiscal, List<Cuenta> cuentas) {
        System.out.println(nombreDeLaEmpresa);
        System.out.println("        Estado de resultados");
        System.out.println("    al 31 de diciembre del " + añoFiscal);
        System.out.println("-----------------------------------------");

        double totalIngresos = 0.0;
        double totalGastos = 0.0;

        System.out.println("Ingresos:");
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTipoDeCuenta().contains("Ingresos")) {
                totalIngresos += cuenta.getMonto();
                System.out.println(cuenta.imprimir());
            }
        }

        System.out.println("-----------------------------------------");
        System.out.println("Total Ingresos: $" + totalIngresos);
        System.out.println();

        System.out.println("Gastos:");
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTipoDeCuenta().contains("Gastos")) {
                totalGastos += cuenta.getMonto();
                System.out.println(cuenta.imprimir());
            }
        }

        System.out.println("-----------------------------------------");
        System.out.println("Total Gastos: $" + totalGastos);
        System.out.println();

        double utilidadBruta = totalIngresos - totalGastos;
        System.out.println("Utilidad bruta: $" + utilidadBruta);

        // Impuesto a la renta
        double impuestoRenta = utilidadBruta * 0.3;
        System.out.println("Impuesto a la renta (30% de la utilidad bruta): $" + impuestoRenta);

        double utilidadNeta = utilidadBruta - impuestoRenta;
        System.out.println("Utilidad neta: $" + utilidadNeta);
    }

    public static void balanceGeneral(String nombreDeLaEmpresa, int añoFiscal, List<Cuenta> cuentas) {
        double totalActivos = 0.0;
        double totalPasivos = 0.0;
        double totalCapital = 0.0;

        System.out.println(nombreDeLaEmpresa);
        System.out.println("        Balance general");
        System.out.println("    al 31 de diciembre del " + añoFiscal);
        System.out.println("-----------------------------------------");

        // Calcular totales de activos, pasivos y capital
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTipoDeCuenta().contains("Activo")) {
                totalActivos += cuenta.getMonto();
            } else if (cuenta.getTipoDeCuenta().contains("Pasivo")) {
                totalPasivos += cuenta.getMonto();
            } else if (cuenta.getTipoDeCuenta().contains("Capital")) {
                totalCapital += cuenta.getMonto();
            }
        }

        // Imprimir activos
        System.out.println("Activos:");
        System.out.println("Total Activos: $" + totalActivos);
        System.out.println();

        // Imprimir pasivos
        System.out.println("Pasivos:");
        System.out.println("Total Pasivos: $" + totalPasivos);
        System.out.println();

        // Imprimir capital
        System.out.println("Capital:");
        System.out.println("Total Capital: $" + totalCapital);
        System.out.println();

        // Verificar si el balance es equilibrado
        if (totalActivos == totalPasivos + totalCapital) {
            System.out.println("El balance está equilibrado.");
        } else {
            System.out.println("El balance no está equilibrado.");
        }
    }
}