package co.com.practicatdd.entidades;

public class VentaProducto {
    private Producto producto;
    private Integer cantidad;
    private Double totalVenta;

    public VentaProducto(Producto producto, Integer cantidad, Double totalVenta) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.totalVenta = totalVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
}
