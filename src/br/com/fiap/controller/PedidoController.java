package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.Produto;
import br.com.fiap.service.IClienteService;
import br.com.fiap.service.IPedidoService;

@RestController
@RequestMapping("loja")
public class PedidoController {
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("pedido/{id}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") Long id) {
		Pedido pedido = pedidoService.getPedidoById(id);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	@GetMapping("pedidos")
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		List<Pedido> lista = pedidoService.getAllPedidos();
		return new ResponseEntity<List<Pedido>>(lista, HttpStatus.OK);
	}
	@PostMapping("cliente/{idCliente}/pedido")
	public ResponseEntity<Void> addPedido(@PathVariable(value = "idCliente") Long idCliente, @RequestBody Pedido pedido, UriComponentsBuilder builder) {
		Cliente cliente = clienteService.getClienteById(idCliente);
		pedido.setCliente(cliente);
		Pedido savedPedido = pedidoService.addPedido(pedido);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/pedido/{id}").buildAndExpand(savedPedido.id).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PostMapping("pedido/{idPedido}/produto")
	public ResponseEntity<Pedido> addProdutoInPedido(@PathVariable(value = "idPedido") Long idPedido, @RequestBody Produto produto, UriComponentsBuilder builder) {
		Pedido pedido = pedidoService.getPedidoById(idPedido);
		pedido.addProduto(produto);
		pedidoService.updatePedido(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	@PutMapping("pedido")
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) {
		pedidoService.updatePedido(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	@DeleteMapping("pedido/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
		pedidoService.deletePedido(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 