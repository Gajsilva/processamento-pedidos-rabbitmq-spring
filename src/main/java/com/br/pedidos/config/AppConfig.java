package com.br.pedidos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public static final String QUEUE_NAME_ENTREGADOR = "entregador.fila";
    public static final String ROUTING_KEY_ENTREGADOR = "entregador.key";
    public static final String EXCHANGE_NAME_PEDIDOS = "pedidos.exchange";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registrando módulo para suporte a tipos do Java 8 Date/Time API
        // Outras configurações do ObjectMapper, se necessário
        return objectMapper;
    }

    @Bean
    public Queue entregadorQueue() {
        return new Queue(QUEUE_NAME_ENTREGADOR, true); // true indica que a fila é durável
    }

    @Bean
    public TopicExchange pedidosExchange() {
        return new TopicExchange(EXCHANGE_NAME_PEDIDOS);
    }

    @Bean
    public Binding bindingEntregador(Queue entregadorQueue, TopicExchange pedidosExchange) {
        return BindingBuilder.bind(entregadorQueue).to(pedidosExchange).with(ROUTING_KEY_ENTREGADOR);
    }
}
