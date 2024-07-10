package com.br.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntregadorRequest(String nome, String endereco) implements Serializable {
    // Você pode adicionar métodos adicionais aqui, se necessário
}

