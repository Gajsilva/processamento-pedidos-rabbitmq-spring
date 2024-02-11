package com.br.pedidos.controller;

import com.br.pedidos.entity.Pedido;
import com.br.pedidos.service.PedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criarPedido")
    public String criarPedido(@RequestBody Pedido pedido) {
        pedidoService.processarPedido(pedido);
        return "Pedido criado com sucesso!";
    }
}

