package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.Producto;
import co.com.practicatdd.entidades.Venta;
import co.com.practicatdd.entidades.VentaProducto;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaTest {

    @Test
    public void validarCamposObligatoriosExistan() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 4000.0, 0.16, 4640.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarCamposRequeridos();

        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void cantidadDebeSerMayorACero() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 0, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 4000.0, 0.16, 4640.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarCantidad(venta.getVentaProductos());

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void totalProductoSubTotalTolaVentaDebeSerMayorCero() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, 0.0);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 0.0, 0.16, 4640.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarTotalesVenta();

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void totalVentaDebeSerMayorAlSubtotal() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 4800.0, 0.16, 4640.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarTotalVentaMayorSubtotal();

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void descuentoDebeSerMayorOIgualACero() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 4000.0, 0.16, 4640.0, fechaVenta, 2.0);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarDescuento();

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void subtotalEsIgualSumatoriaDelTotalProducto() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 4000.0, 0.16, 4640.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        double respuesta = ventaNegocio.calcularSubtotal();

        //Assert
        Assert.assertEquals(10500.0, respuesta, 0.0);
    }

    @Test
    public void totalVentaEsSubtotalDescuentoIva() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 10500.0, 0.19, 7938.0, fechaVenta, 0.2);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        double respuesta = ventaNegocio.calcularTotalVenta();

        //Assert
        Assert.assertEquals(9996.0, respuesta, 0.0);
    }

    //La fecha de la venta debe ser igual a la fecha actual

    @Test
    public void ivaEsDiecinuevePorciento() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3, producto.getPrecio() * 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 10500.0, 0.19, 7938.0, fechaVenta);

        //Act
        double respuesta = venta.getIva();

        //Assert
        Assert.assertEquals(0.19, respuesta, 0.0);
    }
}
