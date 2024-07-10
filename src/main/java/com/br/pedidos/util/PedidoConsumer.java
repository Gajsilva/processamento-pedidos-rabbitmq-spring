package com.br.pedidos.util;

import com.br.pedidos.config.AppConfig;
import com.br.pedidos.entity.EntregadorRequest;
import com.br.pedidos.entity.Pedido;
import com.br.pedidos.repository.PedidoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PedidoConsumer {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public PedidoConsumer() {
    }

    @RabbitListener(queues = "${fila.pedidos}")
    public void receberPedido(Long pedidoId) {
        pedidoRepository.findById(pedidoId).ifPresent(pedido -> {
            log.info("Pedido recebido: " + pedido.getDescricao());
            // Aqui você pode processar o pedido conforme necessário
            // Por exemplo, enviar para o entregador
            enviarPedidoParaEntregador(pedido);
        });
    }

    @RabbitListener(queues = AppConfig.QUEUE_NAME_ENTREGADOR)
    public void consumerEntregador(EntregadorRequest entregadorRequest) {
        log.info("Dados do entregador: " + entregadorRequest.toString());
        // Lógica de processamento da mensagem do entregador aqui
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPedidoParaEntregador(Pedido pedido) {
        rabbitTemplate.convertAndSend(AppConfig.EXCHANGE_NAME_PEDIDOS, AppConfig.ROUTING_KEY_ENTREGADOR, pedido);
    }
}
