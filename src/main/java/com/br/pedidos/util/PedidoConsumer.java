package com.br.pedidos.util;

import com.br.pedidos.entity.Pedido;
import com.br.pedidos.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
@Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "${fila.pedidos}")
    public void receberPedido(Long pedidoId) {

        pedidoRepository.findById(pedidoId).ifPresent(pedido -> System.out.println("Pedido recebido: " + pedido.getDescricao()));
    }
}
