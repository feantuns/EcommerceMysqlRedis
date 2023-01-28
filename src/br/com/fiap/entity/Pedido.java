package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
	public long id;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "cliente", referencedColumnName="id", nullable = false)
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(name = "pedido_produtos",
          joinColumns = { @JoinColumn(name = "id_pedido", referencedColumnName = "id") },
          inverseJoinColumns = { @JoinColumn(name = "id_produto", referencedColumnName = "id") })
    private Set<Produto> produtos = new HashSet<>();
    
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void addProduto(Produto produto) {
	    this.produtos.add(produto);
	    produto.getPedidos().add(this);
	}
	  
	public void removeProduto(long produtoId) {
	    Produto produto = this.produtos.stream().filter(p -> p.getId() == produtoId).findFirst().orElse(null);
	    if (produto != null) {
	    	this.produtos.remove(produto);
	    	produto.getPedidos().remove(this);
	    }
	}

	public Pedido() {
	}

}