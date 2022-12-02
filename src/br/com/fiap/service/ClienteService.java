package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.fiap.entity.Cliente;
import br.com.fiap.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {
	@Autowired
	private ClienteRepository ClienteRepository;
	
	@Override	
	@Cacheable(value= "ClienteCache", key= "#id")		
	public Cliente getClienteById(long id) {
		System.out.println("getClienteById()");		
		return ClienteRepository.findById(id).get();
	}
	@Override	
	@Cacheable(value= "allClientesCache", unless= "#result.size() == 0")	
	public List<Cliente> getAllClientes(){
		System.out.println("getAllClientes()");
		List<Cliente> lista = new ArrayList<>();
		ClienteRepository.findAll().forEach(e -> lista.add(e));
		return lista;
	}
	@Override	
	@Caching(
		put= { @CachePut(value= "ClienteCache", key= "#Cliente.id") },
		evict= { @CacheEvict(value= "allClientesCache", allEntries= true) }
	)
	public Cliente addCliente(Cliente Cliente){
		System.out.println("addCliente()");		
		return ClienteRepository.save(Cliente);
	}
	@Override	
	@Caching(
		put= { @CachePut(value= "ClienteCache", key= "#Cliente.id") },
		evict= { @CacheEvict(value= "allClientesCache", allEntries= true) }
	)
	public Cliente updateCliente(Cliente Cliente) {
		System.out.println("addCliente()");		
		return ClienteRepository.save(Cliente);
	}
	@Override	
	@Caching(
		evict= { 
			@CacheEvict(value= "ClienteCache", key= "#id"),
			@CacheEvict(value= "allClientesCache", allEntries= true)
		}
	)
	public void deleteCliente(long id) {
		System.out.println("deleteCliente()");		
		ClienteRepository.delete(ClienteRepository.findById(id).get());
	}
} 