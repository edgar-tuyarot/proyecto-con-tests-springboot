package com.limpiezait.service.interfaces;

import com.limpiezait.entity.Pedido;
import com.limpiezait.entity.Producto;
import com.limpiezait.entity.ProductoPedido;

public interface ProductoPedidoService {

    ProductoPedido buscarPorId(Long id);

    ProductoPedido aumentarCantidadProducto(Producto producto, Pedido pedido);

}
