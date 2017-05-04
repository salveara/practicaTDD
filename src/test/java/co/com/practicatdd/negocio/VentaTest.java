package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.Producto;
import co.com.practicatdd.entidades.Venta;
import co.com.practicatdd.entidades.VentaProducto;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoCliente;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;

import co.com.practicatdd.repositorio.ClienteRepositorioImpl;
import co.com.practicatdd.repositorio.VentaRepositorio;
import co.com.practicatdd.repositorio.VentaRepositorioImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VentaTest {

    private Cliente cliente;
    private VentaProducto ventaProducto;
    private List<VentaProducto> ventaProductos;
    private Venta venta;
    private VentaNegocio ventaNegocio;

    /**
     * Este metodo es el arrange de la mayoria de pruebas  la etiqueta @Before hace que se ejecute antes de cada prueba
     */
    @Before
    public void init() {
        cliente = new Cliente("Jhon", "Doe", TipoDocumento.TARJETA_IDENTIDAD, "3111258",
                "3103125685", "jhon@gmail.com", Genero.HOMBRE, TipoCliente.ORO);
        Producto producto = new Producto("Arroz", 3500.0);
        ventaProducto = new VentaProducto(producto, 3);
        ventaProductos = new ArrayList<VentaProducto>();
        ventaProductos.add(ventaProducto);
        venta = new Venta(cliente, ventaProductos);
        ClienteNegocio clienteNegocio = new ClienteNegocio(cliente, new ClienteRepositorioImpl());
        VentaRepositorio ventaRepositorio = new VentaRepositorioImp();
        ventaNegocio = new VentaNegocio(venta, ventaRepositorio, clienteNegocio);
    }

    @Test
    public void validarCamposObligatoriosExistan() {
        //Act
        boolean respuesta = ventaNegocio.validarCamposRequeridos();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void cantidadDebeSerMayorACero() {
        //Arrange
        ventaProducto.setCantidad(0);
        //Act
        boolean respuesta = ventaNegocio.validarCantidad();
        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void totalProductoSubTotalTolaVentaDebeSerMayorCero() {
        //Act
        boolean respuesta = ventaNegocio.validarTotalesVenta();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void totalVentaDebeSerMayorAlSubtotal() {
        //Act
        boolean respuesta = ventaNegocio.validarTotalVentaMayorSubtotal();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void descuentoDebeSerMayorOIgualACero() {
        //Act
        boolean respuesta = ventaNegocio.validarDescuento();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void subtotalEsIgualSumatoriaDelTotalProducto() {
        //Act
        double respuesta = venta.calcularSubtotal();
        //Assert
        Assert.assertEquals(10500.0, respuesta, 0.0);
    }

    @Test
    public void totalVentaEsSubtotalDescuentoIva() {
        //Arrange
        venta.setDescuento(0.2);
        //Act
        double respuesta = venta.getTotalVenta();
        //Assert
        Assert.assertEquals(12495.0, respuesta, 0.0);
    }

    @Test
    public void fechaDeVentaIgualFechaActual() {
        //Arrange
        Venta venta = new Venta(cliente, ventaProductos);
        long fechaAcutalMillis = new Date().getTime();
        //Act
        long fechaVentaMillis = venta.getFechaVenta().getTime();
        //Assert
        Assert.assertEquals(fechaAcutalMillis, fechaVentaMillis, 5000L);
    }

    @Test
    public void ivaEsDiecinuevePorciento() {
        //Act
        double respuesta = venta.getIva();
        //Assert
        Assert.assertEquals(0.19, respuesta, 0.0);
    }

    @Test
    public void clienteNuevoQueNoDaSuInformacionSeGuardaComoGenerico() {
        //Arrange
        ClienteNegocio clienteNegocio = mock(ClienteNegocio.class);
        when(clienteNegocio.GuardarCliente()).thenReturn("Ocurrió un error al guardar la información.");
        VentaRepositorio ventaRepositorio = mock(VentaRepositorio.class);
        when(ventaRepositorio.guardarVenta(venta)).thenReturn(venta);
        VentaNegocio negocio = new VentaNegocio(venta, ventaRepositorio, clienteNegocio);
        //Act
        String respuesta = negocio.GuardarVenta(false).getCliente().getNombres();
        //Assert
        Assert.assertEquals("Cliente Generico", respuesta);
    }

    @Test
    public void clienteExisteSeGuardaConEseCliente() {
        //Arrange
        ClienteNegocio clienteNegocio = mock(ClienteNegocio.class);
        when(clienteNegocio.GuardarCliente()).thenReturn("El cliente ya existe");
        VentaRepositorio ventaRepositorio = mock(VentaRepositorio.class);
        when(ventaRepositorio.guardarVenta(venta)).thenReturn(venta);
        VentaNegocio negocio = new VentaNegocio(venta, ventaRepositorio, clienteNegocio);
        //Act
        String respuesta = negocio.GuardarVenta(true).getCliente().getNombres();
        //Assert
        Assert.assertEquals("Jhon", respuesta);
    }

    @Test
    public void clienteOroTieneDescuentoDe10PorCientoEnSemanaO15ElFinDeSemana() {
        //Arrange
        double descuento = 0.1;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            descuento = 0.15;
        }
        //Act
        double respuesta = venta.getDescuento();
        //Assert
        Assert.assertEquals(descuento, respuesta, 0.0);
    }

    @Test
    public void clientePlataTieneDescuentoDe5PorCientoEnSemanaO10ElFinDeSemana() {
        //Arrange
        cliente.setTipoCliente(TipoCliente.PLATA);
        Venta venta = new Venta(cliente, ventaProductos);
        double descuento = 0.05;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            descuento = 0.1;
        }
        //Act
        double respuesta = venta.getDescuento();
        //Assert
        Assert.assertEquals(descuento, respuesta, 0.0);
    }

    @Test
    public void clientePorDefectoTieneDescuentoDeMaximo2PorCientoEnSemanaO5ElFinDeSemana() {
        //Arrange
        cliente.setTipoCliente(TipoCliente.DEFECTO);
        Venta venta = new Venta(cliente, ventaProductos, 0.01);
        //Act
        double respuesta = venta.getDescuento();
        //Assert
        Assert.assertEquals(0.01, respuesta, 0.0);
    }
}
