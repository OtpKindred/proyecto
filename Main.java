import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static cuenta[] cuentas = new cuenta[100];
    static int totalCuentas = 0;
    static int siguienteCuenta = 1001;

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n           MENU BANCO          ");
            System.out.println("1.- Crear nueva cuenta");
            System.out.println("2.- Iniciar sesión");
            System.out.println("3.- Salir");
            System.out.print("Elija una opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> crearCuenta();
                case "2" -> iniciarSesion();
                case "3" -> continuar = false;
                default -> System.out.println("Opción inválida, Intente otra vez.");
            }
        }
    }

    static void crearCuenta() {
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese un PIN de 4 dígitos: ");
        String pin = sc.nextLine();

        if (pin.length() != 4) {
            System.out.println("PIN inválido. Debe tener 4 caracteres.");
            return;
        }

        cuenta nueva = new cuenta(nombre, siguienteCuenta, pin);
        cuentas[totalCuentas] = nueva;
        System.out.println("Cuenta creada exitosamente, Su número es: " + siguienteCuenta);
        totalCuentas++;
        siguienteCuenta++;
    }

    static void iniciarSesion() {
        System.out.print("Número de cuenta: ");
        int numCuenta = Integer.parseInt(sc.nextLine());

        System.out.print("PIN: ");
        String pinIngresado = sc.nextLine();

        cuenta cuenta = buscarCuenta(numCuenta);


        if (cuenta != null && cuenta.pin.equals(pinIngresado)) {
            System.out.println("Bienvenido/a, " + cuenta.getNombre());
            menuCuenta(cuenta);
        }
        else {
            System.out.println("Datos incorrectos o cuenta no existente.");
        }
    }

    static cuenta buscarCuenta(int numero) {
        for (int i = 0; i < totalCuentas; i++) {
            if (cuentas[i].numeroCuenta == numero) {
                return cuentas[i];
            }
        }
        return null;
    }

    static void menuCuenta(cuenta cuenta) {
        boolean enSesion = true;

        while (enSesion) {
            System.out.println("\n--- Menú de Cuenta ---");
            System.out.println("1. Ver saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Ver historial");
            System.out.println("5. Cerrar sesión");
            System.out.print("Elija: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" ->
                        System.out.println("Saldo actual: $" + cuenta.saldo);
                case "2" -> {
                    System.out.print("Ingrese monto a depositar: $");
                    double monto = Double.parseDouble(sc.nextLine());
                    cuenta.saldo += monto;
                    cuenta.agregarHistorial("Depósito de $" + monto);
                    System.out.println("Depósito realizado");
                }
                case "3" -> {
                    System.out.print("Ingrese monto a retirar: $");
                    double retiro = Double.parseDouble(sc.nextLine());

                    if (retiro <= cuenta.saldo) {
                        cuenta.saldo -= retiro;
                        cuenta.agregarHistorial("Retiro de $" + retiro);
                        System.out.println("Retiro exitoso");

                    } else {
                        System.out.println("Saldo insuficiente");
                    }
                }
                case "4" -> {
                    System.out.println("Transacciones:");
                    cuenta.mostrarHistorial();
                }
                case "5" -> enSesion = false;

                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
