package com.limpiezait.service.impl;


import com.limpiezait.entity.Pedido;
import com.limpiezait.entity.Producto;
import com.limpiezait.entity.ProductoPedido;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.repository.ProductoPedidoRepository;
import com.limpiezait.service.interfaces.ProductoPedidoService;
import com.limpiezait.service.interfaces.ProductoService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductoPedidoImpl implements ProductoPedidoService {

    private final ProductoPedidoRepository productoPedidoRepository;
    private final ProductoService productoService;


    @Override
    public ProductoPedido buscarPorId(Long id) throws ResourceNotFoundException {
        return productoPedidoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El producto pedido con el id "+id+" no existe"));
    }

    @Override
    public ProductoPedido aumentarCantidadProducto(Producto producto, Pedido pedido, int cantidad) {

        ProductoPedido productoPedido = productoPedidoRepository.findByProductoIdAndPedidoId(producto.getId(), pedido.getId());

        if (productoPedido != null) {
                productoPedido.setCantidad(cantidad);
                productoPedidoRepository.save(productoPedido);
                return productoPedido;

        }else{
            ProductoPedido nuevoPP = ProductoPedido.builder()
                    .cantidad(cantidad)
                    .producto(producto)
                    .pedido(pedido)
                    .precioUnitario(producto.getPrecio())
                    .build();
            productoPedidoRepository.save(nuevoPP);
            return nuevoPP;
        }

    }

    @Override
    public List<ProductoPedido> obtenerTodosDelPedido(Long idPedido) {

       return productoPedidoRepository.findByPedidoId(idPedido);

    }


}
