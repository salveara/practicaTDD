package co.com.practicatdd.entidades;

public class VentaProducto {
    private Producto producto;
    private Integer cantidad;
    private Double totalVenta;

    public VentaProducto(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.totalVenta = producto.getPrecio() * cantidad;
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
}
