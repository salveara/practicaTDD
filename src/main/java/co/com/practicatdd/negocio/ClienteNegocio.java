package co.com.practicatdd.negocio;

import co.com.practicatdd.entidades.Cliente;
import co.com.practicatdd.entidades.enumerator.Genero;
import co.com.practicatdd.entidades.enumerator.TipoDocumento;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by santi on 25/04/2017.
 */
public class ClienteNegocio {
    private Cliente cliente;

    public ClienteNegocio(Cliente informacionCliente) {
        cliente = informacionCliente;
    }

    public boolean ValidarCampos() {
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
        return !(cliente.getNombres() == null || "".equals(cliente.getNombres()))
                && !(cliente.getApellidos() == null || "".equals(cliente.getApellidos()))
                && !(cliente.getApellidos() == null || "".equals(cliente.getApellidos()))
                && cliente.getTipoDocumento() != TipoDocumento.NINGUNO
                && !(cliente.getNumeroDocumento() == null || "".equals(cliente.getNumeroDocumento()))
                && !(cliente.getTelefonoCelular() == null || "".equals(cliente.getTelefonoCelular()))
                && !(cliente.getCorreoElectronico() == null || "".equals(cliente.getCorreoElectronico()))
                && cliente.getGenero() != Genero.NINGUNO;
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

    public int calcularEdad() {
        LocalDate now = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.fromDateFields(cliente.getFechaNacimiento());
        Period period = new Period(fechaNacimiento, now, PeriodType.yearMonthDay());
        return period.getYears();
    }

    public boolean GuardarInformacion() {
        if (cliente.getFechaNacimiento() != null) {
            System.out.println(EdadCliente());
        }
        if (!ValidarCampos()) {
            return false;
        }
        return true;
    }
}
