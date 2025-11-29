package com.limpiezait.service.impl;


import com.limpiezait.entity.Pedido;
import com.limpiezait.entity.Producto;
import com.limpiezait.entity.ProductoPedido;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.repository.ProductoPedidoRepository;
import com.limpiezait.service.interfaces.ProductoPedidoService;
import com.limpiezait.service.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
    public ProductoPedido aumentarCantidadProducto(Producto producto, Pedido pedido) {

        ProductoPedido productoPedido = productoPedidoRepository.findByProductoIdAndPedidoId(producto.getId(), pedido.getId());
        if (productoPedido != null) {
            if (productoPedido.getCantidad() > 0) {
                productoPedido.setCantidad(productoPedido.getCantidad() + 1);
                productoPedidoRepository.save(productoPedido);
                return productoPedido;
            } else {
                productoPedido.setCantidad(1);
                productoPedidoRepository.save(productoPedido);
                return productoPedido;
            }
        }else{
            ProductoPedido nuevoPP = ProductoPedido.builder()
                    .cantidad(1)
                    .producto(producto)
                    .pedido(pedido)
                    .precioUnitario(producto.getPrecio())
                    .build();
            productoPedidoRepository.save(nuevoPP);
            return nuevoPP;
        }

    }

    //Viendo los pedidos, retomar en buscar un ProPedido,y si no esta, crearlo si esta el PP con idPEdido y IDprod, sumar 1 en cantidad.
}
