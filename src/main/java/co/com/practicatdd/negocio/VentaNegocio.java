package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Venta;

/**
 * Created by santi on 25/04/2017.
 */
public class VentaNegocio {

    private Venta venta;

    VentaNegocio(Venta venta) {
        this.venta = venta;
    }

    public boolean validarCamposRequeridos() {
        return !(venta.getCliente() == null)
                && !(venta.getNombreProducto() == null || "".equals(venta.getNombreProducto()))
                && !(venta.getCantidad() == null)
                && !(venta.getPrecio() == null)
                && !(venta.getTotalVentaProducto() == null)
                && !(venta.getSubtotal() == null)
                && !(venta.getIva() == null)
                && !(venta.getTotalVenta() == null)
                && !(venta.getFechaVenta() == null);
    }

    public boolean validarCantidad() {
        return (venta.getCantidad() > 0);
    }

    public boolean validarTotales() {
        return (venta.getTotalVentaProducto() > 0
            && venta.getTotalVenta() > 0
            && venta.getSubtotal() > 0);
    }

    public boolean validarTotalVentaMayorSubtotal() {
        return venta.getTotalVenta() > venta.getSubtotal();
    }

    public boolean validarDescuento() {
        return venta.getDescuento() >= 0;
    }

    public boolean validarSubtotalIgualTotalProducto() {
            return venta.getSubtotal().equals(venta.getTotalVentaProducto());
    }
}
