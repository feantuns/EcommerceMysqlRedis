package br.com.fiap.service;

import java.util.List;

import br.com.fiap.entity.Cliente;

public interface IClienteService {
	List<Cliente> getAllClientes();
    Cliente getClienteById(long id);
    Cliente addCliente(Cliente Cliente);
    Cliente updateCliente(Cliente Cliente);
    void deleteCliente(long id);
}
