package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.Producto;
import co.com.practicatdd.entidades.Venta;
import co.com.practicatdd.entidades.VentaProducto;
import co.com.practicatdd.repositorio.VentaRepositorioImp;

public class VentaNegocio {

    private Venta venta;
    private ClienteNegocio clienteNegocio;

    VentaNegocio(Venta venta) {
        this.venta = venta;
    }

    VentaNegocio(Venta venta, ClienteNegocio clienteNegocio) {
        this.venta = venta;
        this.clienteNegocio = clienteNegocio;
    }

    public boolean validarCamposRequeridos() {
        return (validarCamposRequeridosVentaProducto())
                && (venta.getCliente() != null)
                && (venta.getSubtotal() != null)
                && (venta.getIva() != null)
                && (venta.getTotalVenta() != null)
                && (venta.getFechaVenta() != null);
    }

    private boolean validarCamposRequeridosVentaProducto() {
        for (VentaProducto ventaProducto: venta.getVentaProductos()) {
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

    public boolean validarCantidad() {
        for (VentaProducto ventaProducto: venta.getVentaProductos()) {
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
                && validarTotalesVentaProducto());
    }

    public boolean validarTotalesVentaProducto() {
        for (VentaProducto ventaProducto: venta.getVentaProductos()) {
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

    public boolean validarTodosLosCampos() {
        return validarCamposRequeridos()
                && validarCantidad()
                && validarTotalesVenta()
                && validarTotalVentaMayorSubtotal()
                && validarDescuento();
    }

    public Venta GuardarVenta(boolean deseaDarInformacion) {
        VentaRepositorioImp ventaRepositorioImp = new VentaRepositorioImp();
        if (deseaDarInformacion){
            if (validarTodosLosCampos()) {
                clienteNegocio.GuardarCliente();
                ventaRepositorioImp.guardarVenta(venta);
                return venta;
            }
            return null;
        }
        Cliente clienteGenerico = new Cliente();
        clienteGenerico.setNombres("Cliente Generico");
        venta.setCliente(clienteGenerico);
        ventaRepositorioImp.guardarVenta(venta);
        return venta;
    }
}
