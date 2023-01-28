package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.fiap.entity.Pedido;
import br.com.fiap.repository.PedidoRepository;

@Service
public class PedidoService implements IPedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override	
	@Cacheable(value= "pedidoCache", key= "#id")		
	public Pedido getPedidoById(long id) {
		System.out.println("getPedidoById()");		
		return pedidoRepository.findById(id).get();
	}
	@Override	
	@Transactional
	@Cacheable(value= "allPedidosCache", unless= "#result.size() == 0")	
	public List<Pedido> getAllPedidos(){
		System.out.println("getAllPedidos()");
		List<Pedido> lista = new ArrayList<>();
		pedidoRepository.findAll().forEach(e -> lista.add(e));
		return lista;
	}
	@Override	
	@Transactional
	@Caching(
		put= { @CachePut(value= "pedidoCache", key= "#pedido.id") },
		evict= { @CacheEvict(value= "allPedidosCache", allEntries= true) }
	)
	public Pedido addPedido(Pedido pedido){
		System.out.println("addPedido()");		
		return pedidoRepository.save(pedido);
	}
	@Override	
	@Caching(
		put= { @CachePut(value= "pedidoCache", key= "#pedido.id") },
		evict= { @CacheEvict(value= "allPedidosCache", allEntries= true) }
	)
	public Pedido updatePedido(Pedido pedido) {
		System.out.println("addPedido()");		
		return pedidoRepository.save(pedido);
	}
	@Override	
	@Caching(
		evict= { 
			@CacheEvict(value= "pedidoCache", key= "#id"),
			@CacheEvict(value= "allPedidosCache", allEntries= true)
		}
	)
	public void deletePedido(long id) {
		System.out.println("deletePedido()");		
		pedidoRepository.delete(pedidoRepository.findById(id).get());
	}
} 