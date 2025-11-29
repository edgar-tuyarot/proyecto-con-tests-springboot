package com.limpiezait.repository;

import com.limpiezait.entity.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Long> {

    ProductoPedido findByProductoIdAndPedidoId(Long productoId, Long pedidoId);

}
