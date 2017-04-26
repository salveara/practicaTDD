package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.Venta;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by santi on 25/04/2017.
 */
public class VentaTest {

    @Test
    public void validarCamposObligatoriosExistan() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Venta venta = new Venta(cliente, "Jabon", 2, 2000.0, 4000.0,
                        4000.0, 0.16, 4640.0, fechaVenta);
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
        Venta venta = new Venta(cliente, "Jabon", 0, 2000.0, 4000.0,
                4000.0, 0.16, 4640.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarCantidad();

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
        Venta venta = new Venta(cliente, "Jabon", 2, 2000.0, 4000.0,
                4000.0, 0.16, 0.0, fechaVenta);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarTotales();

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
        Venta venta = new Venta(cliente, "Jabon", 2, 2000.0, 4000.0,
                4000.0, 0.16, 0.0, fechaVenta);
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
        Venta venta = new Venta(cliente, "Jabon", 2, 2000.0, 4000.0,
                4000.0, 0.16, 4640.0, fechaVenta);
        venta.setDescuento(0.0);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarDescuento();

        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void subtotalEsIgualSumatoriaDelTotalProducto() {
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        Date fechaVenta = new Date();
        Venta venta = new Venta(cliente, "Jabon", 2, 2000.0, 4000.0,
                4000.0, 0.16, 4640.0, fechaVenta);
        venta.setDescuento(0.0);
        VentaNegocio ventaNegocio = new VentaNegocio(venta);

        //Act
        boolean respuesta = ventaNegocio.validarSubtotalIgualTotalProducto();

        //Assert
        Assert.assertTrue(respuesta);
    }
}
