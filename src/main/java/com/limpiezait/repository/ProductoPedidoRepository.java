package com.limpiezait.repository;

import com.limpiezait.entity.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Long> {

    ProductoPedido findByProductoIdAndPedidoId(Long productoId, Long pedidoId);

    List<ProductoPedido> findByPedidoId(Long pedidoId);


}

