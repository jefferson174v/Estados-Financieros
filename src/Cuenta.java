abstract class Cuenta {
    protected String nombre;
    protected String tipoDeCuenta;
    protected double monto;

    public Cuenta(String nombre, String tipoDeCuenta, double monto) {
        this.nombre = nombre;
        this.tipoDeCuenta = tipoDeCuenta;
        this.monto = monto;
    }

    public abstract String imprimir();

    public String getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public double getMonto() {
        return monto;
    }
}

class Activos extends Cuenta {
    public Activos(String nombre, String tipoDeCuenta, double monto) {
        super(nombre, tipoDeCuenta, monto);
    }

    @Override
    public String imprimir() {
        return "Nombre: " + nombre + ", Tipo: " + tipoDeCuenta + ", Monto: $" + monto;
    }
}