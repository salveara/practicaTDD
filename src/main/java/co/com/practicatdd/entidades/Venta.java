package co.com.practicatdd.entidades;

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
    }

    public Venta(Cliente cliente, List<VentaProducto> ventaProductos, Double descuento) {
        this.cliente = cliente;
        this.ventaProductos = ventaProductos;
        this.subtotal = calcularSubtotal();
        this.descuento = descuento;
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
