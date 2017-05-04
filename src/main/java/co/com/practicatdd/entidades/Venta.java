package co.com.practicatdd.entidades;

import co.com.practicatdd.entidades.enumerator.TipoCliente;

import java.util.Date;
import java.util.List;

public class Venta {
    private Cliente cliente;
    private List<VentaProducto> ventaProductos;
    private Double subtotal;
    private Double iva = 0.19;
    private Double totalVenta;
    private Date fechaVenta;
    private Double descuento;

    public Venta(Cliente cliente, List<VentaProducto> ventaProductos) {
        this.cliente = cliente;
        this.ventaProductos = ventaProductos;
        this.subtotal = calcularSubtotal();
        this.totalVenta = subtotal * (1 + iva);
        this.fechaVenta = new Date();
        this.descuento = calcularDescuento();
    }

    public Venta(Cliente cliente, List<VentaProducto> ventaProductos, Double descuento) {
        this.cliente = cliente;
        this.ventaProductos = ventaProductos;
        this.subtotal = calcularSubtotal();
        if ("DEFECTO".equals(cliente.getTipoCliente().toString())
                && descuento < ((TipoCliente.DEFECTO.getDescuento() == 0.5)?0.5:0.2)) {
            this.descuento = descuento;
        } else {
            this.descuento = calcularDescuento();
        }
        this.totalVenta = subtotal * (1 - descuento) * (1 + iva);
        this.fechaVenta = new Date();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (VentaProducto ventaProducto: ventaProductos) {
            subtotal += ventaProducto.getTotalVenta();
        }
        return subtotal;
    }

    private double calcularDescuento() {
        switch (cliente.getTipoCliente()) {
            case ORO: return TipoCliente.ORO.getDescuento();
            case PLATA: return TipoCliente.PLATA.getDescuento();
            default: return TipoCliente.DEFECTO.getDescuento();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<VentaProducto> getVentaProductos() {
        return ventaProductos;
    }

    public void setVentaProductos(List<VentaProducto> ventaProductos) {
        this.ventaProductos = ventaProductos;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
}
