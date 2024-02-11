package com.br.pedidos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue filaPedidos(@Value("${fila.pedidos}") String filaPedidos) {
        return new Queue(filaPedidos);
    }
}
