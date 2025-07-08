public class cuenta {
    private String nombreTitular;
    int numeroCuenta;
    String pin;
    double saldo;
    private String historial = "";

    cuenta(String nombre, int cuenta, String pin) {
        this.setNombre(nombre);
        this.numeroCuenta = cuenta;
        this.pin = pin;
        this.saldo = 0;
    }

    public void setNombre(String nombre) {
        this.nombreTitular = nombre;
    }

    public String getNombre() {
        return nombreTitular;
    }

    void agregarHistorial(String mensaje) {
        historial += mensaje + "\n";
    }

    void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay transacciones aún.");
        }
        else {
            System.out.print(historial);
        }
    }
}
