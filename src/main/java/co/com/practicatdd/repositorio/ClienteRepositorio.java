package co.com.practicatdd.repositorio;

import co.com.practicatdd.entidades.Cliente;

public interface ClienteRepositorio {
    boolean validarUsuarioExistente(String numeroDocumento);

    Cliente guardarCliente(Cliente cliente);
}
