package com.br.pedidos.repository;

import com.br.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByOrderByDataCriacaoDesc();

    List<Pedido> findByDescricaoContainingIgnoreCase(String descricao);
}
