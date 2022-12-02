package br.com.fiap.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.fiap.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}