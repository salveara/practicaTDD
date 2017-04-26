package co.com.practicatdd.entidades;

import java.util.Date;

/**
 * Created by santi on 25/04/2017.
 */
public class Venta {

    private Cliente cliente;
    private String nombreProducto;
    private Integer cantidad;
    private Double precio;
    private Double totalVentaProducto;
    private Double subtotal;
    private Double iva;
    private Double totalVenta;
    private Date fechaVenta;
    private Double descuento;

    public Venta() {
    }

    public Venta(Cliente cliente, String nombreProducto, int cantidad, double precio, double totalVentaProducto, double subtotal, double iva, double totalVenta, Date date) {
        this.cliente = cliente;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.totalVentaProducto = totalVentaProducto;
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalVenta = totalVenta;
        this.fechaVenta = date;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotalVentaProducto() {
        return totalVentaProducto;
    }

    public void setTotalVentaProducto(Double totalVentaProducto) {
        this.totalVentaProducto = totalVentaProducto;
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
