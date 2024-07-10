package com.br.pedidos.service;

import com.br.pedidos.entity.Pedido;
import com.br.pedidos.repository.PedidoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PedidoService {
@Autowired
    private  RabbitTemplate rabbitTemplate;

    @Value("${fila.pedidos}")
    private String filaPedidos;
    @Autowired
    private  PedidoRepository pedidoRepository;


    public void processarPedido(Pedido pedido) {
        // Salva o pedido no banco de dados
        pedidoRepository.save(pedido);
        // Coloca as informações do pedido na fila do RabbitMQ
        rabbitTemplate.convertAndSend(filaPedidos, pedido.getId());
    }

    public void processarPedidos(List<Pedido> pedidos) {

        pedidoRepository.saveAll(pedidos);

        // Coloca as informações dos pedidos na fila do RabbitMQ
        pedidos.forEach(pedido -> rabbitTemplate.convertAndSend(filaPedidos, pedido.getId()));
    }
    public Pedido obterPedidoPorId(Long pedidoId) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        return pedidoOptional.orElseThrow(() -> new NoSuchElementException("Pedido não encontrado com ID: " + pedidoId));
    }


    public List<Pedido> buscarPedidosPorDescricao(String descricao) {
        return pedidoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public void deletarPedido(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }

    public List<Pedido> listarPedidosOrdenadosPorData() {
        return pedidoRepository.findAllByOrderByDataCriacaoDesc();
    }
}
