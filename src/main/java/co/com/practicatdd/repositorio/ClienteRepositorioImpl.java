package co.com.practicatdd.repositorio;

import co.com.practicatdd.entidades.Cliente;

public class ClienteRepositorioImpl implements ClienteRepositorio {

    public boolean validarUsuarioExistente(String numeroDocumento) {
        return true;
    }

    public Cliente guardarCliente(Cliente cliente) {
        return cliente;
    }
}
