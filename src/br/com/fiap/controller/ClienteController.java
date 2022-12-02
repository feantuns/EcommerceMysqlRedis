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
import br.com.fiap.service.IClienteService;

@RestController
@RequestMapping("usuarios")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("cliente/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.getClienteById(id);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	@GetMapping("clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> lista = clienteService.getAllClientes();
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
	}
	@PostMapping("cliente")
	public ResponseEntity<Void> addCliente(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
		Cliente savedCliente = clienteService.addCliente(cliente);  
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/cliente/{id}").buildAndExpand(savedCliente.id).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("cliente")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
		clienteService.updateCliente(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	@DeleteMapping("cliente/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
		clienteService.deleteCliente(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 