package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Producto;
import co.com.practicatdd.entidades.Venta;
import co.com.practicatdd.entidades.VentaProducto;

import java.util.Date;
import java.util.List;

public class VentaNegocio {

    private Venta venta;

    VentaNegocio(Venta venta) {
        this.venta = venta;
    }

    public boolean validarCamposRequeridos() {
        return (validarCamposRequeridosVentaProducto(venta.getVentaProductos()))
                && (venta.getCliente() != null)
                && (venta.getSubtotal() != null)
                && (venta.getIva() != null)
                && (venta.getTotalVenta() != null)
                && (venta.getFechaVenta() != null);
    }

    private boolean validarCamposRequeridosVentaProducto(List<VentaProducto> ventaProductos) {
        for (VentaProducto ventaProducto: ventaProductos) {
            boolean campofaltante = validarCamposRequeridosProducto(ventaProducto.getProducto())
                    && (ventaProducto.getCantidad() != null)
                    && (ventaProducto.getTotalVenta() != null);
            if (!campofaltante) {
                return false;
            }
        }
        return true;
    }

    public boolean validarCamposRequeridosProducto(Producto producto) {
        return (stringIsNotNullOrEmpty(producto.getNombre())
                && (producto.getPrecio() != null));
    }

    private boolean stringIsNotNullOrEmpty(String field) {
        return field != null && !"".equals(field);
    }

    public boolean validarCantidad(List<VentaProducto> ventaProductos) {
        for (VentaProducto ventaProducto: ventaProductos) {
            boolean cantidadMayorCero = ventaProducto.getCantidad() > 0;
            if (!cantidadMayorCero) {
                return false;
            }
        }
        return true;
    }

    public boolean validarTotalesVenta() {
        return (venta.getTotalVenta() > 0
                && venta.getSubtotal() > 0
                && validarTotalesVentaProducto(venta.getVentaProductos()));
    }

    public boolean validarTotalesVentaProducto(List<VentaProducto> ventaProductos) {
        for (VentaProducto ventaProducto: ventaProductos) {
            boolean totalMayorCero = ventaProducto.getTotalVenta() > 0;
            if (!totalMayorCero) {
                return false;
            }
        }
        return true;
    }

    public boolean validarTotalVentaMayorSubtotal() {
        return venta.getTotalVenta() > venta.getSubtotal();
    }

    public boolean validarDescuento() {
        return venta.getDescuento() >= 0 && venta.getDescuento() <= 1;
    }

    public Date fechaActual() {
        return null;
    }
}
