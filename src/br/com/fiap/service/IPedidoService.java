package br.com.fiap.service;

import java.util.List;
import br.com.fiap.entity.Pedido;

public interface IPedidoService {
     List<Pedido> getAllPedidos();
     Pedido getPedidoById(long id);
     Pedido addPedido(Pedido pedido);
     Pedido updatePedido(Pedido pedido);
     void deletePedido(long id);
} 
