package com.br.pedidos.controller;

import com.br.pedidos.entity.Pedido;
import com.br.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @PostMapping("/criarPedidos")
    public ResponseEntity<String> criarPedidos(@RequestBody List<Pedido> pedidos) {
        pedidoService.processarPedidos(pedidos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedidos criados com sucesso!");
    }
    @PostMapping("/criarPedido")
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
        pedidoService.processarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso!");
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.obterPedidoPorId(pedidoId);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/buscarPorDescricao")
    public ResponseEntity<List<Pedido>> buscarPedidosPorDescricao(@RequestParam String descricao) {
        List<Pedido> pedidos = pedidoService.buscarPedidosPorDescricao(descricao);
        return ResponseEntity.ok(pedidos);
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<String> deletarPedido(@PathVariable Long pedidoId) {
        pedidoService.deletarPedido(pedidoId);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");
    }

    @GetMapping("/listarOrdenadosPorData")
    public ResponseEntity<List<Pedido>> listarPedidosOrdenadosPorData() {
        List<Pedido> pedidos = pedidoService.listarPedidosOrdenadosPorData();
        return ResponseEntity.ok(pedidos);
    }
}
