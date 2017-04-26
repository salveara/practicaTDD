package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by santi on 25/04/2017.
 */
public class ClienteTest {

    @Test
    public void SiTodosLosCamposRequeridosTienenDatosSePuedeGuardarLaInformacion() {
        //Arange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();

        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void LosCamposOpcionalesPuedenFaltarYLosDatosSeDebenGuardar(){
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        cliente.setTelefonoCasa("2355412");
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();

        //Assert
        Assert.assertTrue(respuesta);
    }

    @Test
    public void ElTipoDeDocumentoNoPuedeSerNiguno(){
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.NINGUNO, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean respuesta = negocio.ValidarCamposRequeridos();

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void SiElTipoDeDocumentoEsNitSeDebeSolicitarTelefonoDeLaEmpresa(){
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.NIT, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean respuesta = negocio.ValidarTelefonoEmpresa();

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void ElCampoGeneroPuedeSerOtro(){
        //Arrange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.OTRO);
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
        cliente.setCorreoElectronico("albertochanci@gmail.com");
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean resultado = negocio.ValidarFormatoCorreoElectronico();

        //Assert
        Assert.assertTrue(resultado);
    }

    @Test
    public void LaFechaDeNacimientoNoDebeSerMayorALaFechaActual(){
        //Arange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.HOMBRE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "15/10/3000";
        try {
            Date date = simpleDateFormat.parse(dateInString);
            cliente.setFechaNacimiento(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean respuesta = negocio.ValidarFechaNacimiento();

        //Assert
        Assert.assertFalse(respuesta);
    }

    @Test
    public void SiClienteEsMujerEdadEsCero() {
        //Arange
        Cliente cliente = new Cliente("Ana", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail.com", Genero.MUJER);
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        String respuesta = negocio.EdadCliente();

        //Assert
        Assert.assertEquals("Edad: 0", respuesta);
    }

//    Si el usuario ya existe se debe mostrar un mensaje “El cliente ya existe”, de lo contrario se debe
//    permitir guardar la información y mostrar el mensaje “La información ha sido guardada con éxito”


    @Test
    public void CuandoLaInformacionNoEsValidaNoSePuedeGuardar() {
        //Arange
        Cliente cliente = new Cliente("Alberto Chanci", "Chanci",
                TipoDocumento.CEDULA, "999999999", "310000000",
                "albertochanci@gmail", Genero.HOMBRE);
        ClienteNegocio negocio = new ClienteNegocio(cliente);

        //Act
        boolean resultado = negocio.GuardarInformacion();

        //Assert
        Assert.assertFalse(resultado);
    }

//    Si ocurre un error al guardar la información del cliente se debe guardar registro del error y se debe
//    mostrar el mensaje “Ocurrió un error al guardar la información.”
}
