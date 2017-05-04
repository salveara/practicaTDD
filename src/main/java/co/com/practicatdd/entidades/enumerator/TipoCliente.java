package co.com.practicatdd.entidades.enumerator;

import java.util.Calendar;

public enum TipoCliente {
    ORO(0.1), PLATA(0.05), DEFECTO(0.0);

    private double descuento;

    TipoCliente(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return this.descuento + 0.05;
        }
        return descuento;
    }
}
