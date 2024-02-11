package com.br.pedidos.service;

import com.br.pedidos.entity.Pedido;
import com.br.pedidos.repository.PedidoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
