package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;
import co.com.practicatdd.repositorio.ClienteRepositorio;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteNegocio {
    private Cliente cliente;
    private ClienteRepositorio repositorio;

    public ClienteNegocio(Cliente informacionCliente, ClienteRepositorio repositorio) {
        this.cliente = informacionCliente;
        this.repositorio = repositorio;
    }

    private boolean ValidarCampos() {
        if (!ValidarCamposRequeridos()) {
            return false;
        }
        if (!ValidarTelefonoEmpresa()) {
            return false;
        }
        if (!ValidarFormatoCorreoElectronico()) {
            return false;
        }
        if (cliente.getFechaNacimiento() != null) {
            if (!ValidarFechaNacimiento()) {
                return false;
            }
        }
        return true;
    }

    public boolean ValidarCamposRequeridos() {
        return  ((stringIsNotNullOrEmpty(cliente.getNombres()))
                && (stringIsNotNullOrEmpty(cliente.getApellidos()))
                && (stringIsNotNullOrEmpty(cliente.getApellidos()))
                && cliente.getTipoDocumento() != TipoDocumento.NINGUNO
                && (stringIsNotNullOrEmpty(cliente.getNumeroDocumento()))
                && (stringIsNotNullOrEmpty(cliente.getTelefonoCelular()))
                && (stringIsNotNullOrEmpty(cliente.getCorreoElectronico()))
                && cliente.getGenero() != Genero.NINGUNO);
    }

    private boolean stringIsNotNullOrEmpty(String field) {
        return !(field == null || "".equals(field));
    }

    public String imprimirMensajeCamposRequeridos() {
        String mensaje = "";
        if (!ValidarCamposRequeridos()) {
            mensaje = "El campo es requerido";
            System.out.println(mensaje);
        }
        return mensaje;
    }

    public boolean ValidarTelefonoEmpresa() {
        return !("NIT".equals(cliente.getTipoDocumento().toString()) &&
                (cliente.getTelefonoEmpresa() == null || "".equals(cliente.getTelefonoEmpresa())));
    }

    public boolean ValidarFechaNacimiento() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        return (currentDate.getTime() >= cliente.getFechaNacimiento().getTime());
    }

    public boolean ValidarFormatoCorreoElectronico() {
        return cliente.getCorreoElectronico().matches("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$");
    }

    public String EdadCliente() {
        if ("MUJER".equals(cliente.getGenero().toString())) {
            return "Edad: " + 0;
        }
        return "Edad: " + calcularEdad();
    }

    private int calcularEdad() {
        LocalDate now = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.fromDateFields(cliente.getFechaNacimiento());
        Period period = new Period(fechaNacimiento, now, PeriodType.yearMonthDay());
        return period.getYears();
    }

    public boolean GuardarInformacion() {
        if (cliente.getFechaNacimiento() != null) {
            System.out.println(EdadCliente());
        }
        if (ValidarCampos()) {
            if ("La información ha sido guardada con éxito".equals(GuardarCliente())) {
                return true;
            }
        }
        imprimirMensajeCamposRequeridos();
        return false;
    }

    public String GuardarCliente() {
        if (repositorio.validarUsuarioExistente(cliente.getNumeroDocumento())) {
            return "El cliente ya existe";
        }
        if (repositorio.guardarCliente(cliente) != null) {
            return "La información ha sido guardada con éxito";
        }
        return  "Ocurrió un error al guardar la información.";
    }
}
