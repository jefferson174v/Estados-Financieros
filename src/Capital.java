class Capital extends Cuenta {
    public Capital(String nombre, String tipoDeCuenta, double monto) {
        super(nombre, tipoDeCuenta, monto);
    }

    @Override
    public String imprimir() {
        return "Nombre: " + nombre + ", Tipo: " + tipoDeCuenta + ", Monto: $" + monto;
    }
}