package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoCliente;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;

import co.com.practicatdd.repositorio.ClienteRepositorioImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteTest {

    Cliente cliente;
    ClienteNegocio negocio;

    /**
     * Este metodo es el arrange de la mayoria de pruebas  la etiqueta @Before hace que se ejecute antes de cada prueba
     */
    @Before
    public void init() {
        cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE, TipoCliente.ORO);
        negocio = new ClienteNegocio(cliente);
    }

    @Test
    public void SiTodosLosCamposRequeridosTienenDatosSePuedeGuardarLaInformacion() {
        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void LosCamposOpcionalesPuedenFaltarYLosDatosSeDebenGuardar(){
        //Arrange
        cliente.setTelefonoCasa("2355412");
        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void ElTipoDeDocumentoNoPuedeSerNiguno(){
        //Arrange
        cliente.setTipoDocumento(TipoDocumento.NINGUNO);
        ClienteNegocio negocio = new ClienteNegocio(cliente);
        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();
        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void SiElTipoDeDocumentoEsNitSeDebeSolicitarTelefonoDeLaEmpresa(){
        //Arrange
        cliente.setTipoDocumento(TipoDocumento.NIT);
        ClienteNegocio negocio = new ClienteNegocio(cliente);
        //Act
        boolean respuesta = negocio.ValidarTelefonoEmpresa();
        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void ElCampoGeneroPuedeSerOtro(){
        //Arrange
        cliente.setGenero(Genero.OTRO);
        ClienteNegocio negocio = new ClienteNegocio(cliente);
        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();
        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void ElCorreoElectronicoDebeTenerUnFormatoValido() {
        //Arrange
        Cliente cliente = new Cliente();
        cliente.setCorreoElectronico("albertochancigmail.com");
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean resultado = negocio.ValidarFormatoCorreoElectronico();

        //Assert
        Assert.assertFalse(resultado);
    }

    @Test
    public void LaFechaDeNacimientoNoDebeSerMayorALaFechaActual(){
        //Arange
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "15/10/3000";
        try {
            Date date = simpleDateFormat.parse(dateInString);
            cliente.setFechaNacimiento(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Act
        boolean respuesta = negocio.ValidarFechaNacimiento();
        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void SiClienteEsMujerEdadEsCero() {
        //Arange
        cliente.setGenero(Genero.MUJER);
        ClienteNegocio negocio = new ClienteNegocio(cliente);
        //Act
        String respuesta = negocio.EdadCliente();
        //Assert
        Assert.assertEquals("Edad: 0", respuesta);
    }

    @Test
    public void NoSePuedeGuardarClienteSiYaExiste() {
        //Arange
        ClienteRepositorioImpl clienteRepositorio = mock(ClienteRepositorioImpl.class);
        when(clienteRepositorio.validarUsuarioExistente("999999999")).thenReturn(true);
        ClienteNegocio negocio = new ClienteNegocio(cliente, clienteRepositorio);
        //Act
        String respuesta = negocio.GuardarCliente();
        //Assert
        Assert.assertEquals("El cliente ya existe", respuesta);
    }

    @Test
    public void CuandoLaInformacionNoEsValidaNoSePuedeGuardar() {
        //Arramge
        cliente.setCorreoElectronico("nocumple");
        //Act
        boolean resultado = negocio.GuardarInformacion();
        //Assert
        Assert.assertFalse(resultado);
    }

    @Test
    public void SiOcurreUnErrorGuardandoClienteSeDebeMostrarMensaje() {
        //Arrange
        ClienteRepositorioImpl clienteRepositorio = mock(ClienteRepositorioImpl.class);
        when(clienteRepositorio.validarUsuarioExistente("999999999")).thenReturn(false);
        when(clienteRepositorio.guardarCliente(cliente)).thenReturn(null);
        ClienteNegocio negocio = new ClienteNegocio(cliente, clienteRepositorio);
        //Act
        String respuesta = negocio.GuardarCliente();
        //Assert
        Assert.assertEquals("Ocurrió un error al guardar la información.", respuesta);
    }
}
