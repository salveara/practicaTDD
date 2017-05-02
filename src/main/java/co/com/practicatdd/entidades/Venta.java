package co.com.practicatdd.entidades;

import java.util.Date;
import java.util.List;

public class Venta {
    private Cliente cliente;
    private List<VentaProducto> ventaProductos;
    private Double subtotal;
    private Double iva;
    private Double totalVenta;
    private Date fechaVenta;
    private Double descuento;

    public Venta() {
    }

    public Venta(Cliente cliente, List<VentaProducto> ventaProductos, Double subtotal, Double iva, Double totalVenta, Date fechaVenta) {
        this.cliente = cliente;
        this.ventaProductos = ventaProductos;
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
    }

    public Venta(Cliente cliente, List<VentaProducto> ventaProducto, Double subtotal, Double iva, Double totalVenta, Date fechaVenta, Double descuento) {
        this.cliente = cliente;
        this.ventaProductos = ventaProducto;
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
        this.descuento = descuento;
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

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
}
