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
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos);
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
        VentaProducto ventaProducto = new VentaProducto(producto, 0);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos);
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
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarTotalesVenta();

        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void totalVentaDebeSerMayorAlSubtotal() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarTotalVentaMayorSubtotal();

        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void descuentoDebeSerMayorOIgualACero() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 2.0);
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
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos);

        //Act
        double respuesta = venta.calcularSubtotal();

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
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 0.2);

        //Act
        double respuesta = venta.getTotalVenta();

        //Assert
        Assert.assertEquals(9996.0, respuesta, 0.0);
    }

    @Test
    public void fechaDeVentaIgualFechaActual() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos, 0.2);

        //Act
        Date respuesta = venta.getFechaVenta();

        //Assert
        Assert.assertEquals(new Date(), respuesta);
    }

    @Test
    public void ivaEsDiecinuevePorciento() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Producto producto = new Producto("Arroz", 3500.0);
        VentaProducto ventaProducto = new VentaProducto(producto, 3);
        List<VentaProducto> ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        Venta venta = new Venta(cliente, ventaProductos);

        //Act
        double respuesta = venta.getIva();

        //Assert
        Assert.assertEquals(0.19, respuesta, 0.0);
    }
}
