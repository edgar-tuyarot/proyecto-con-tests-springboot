package com.limpiezait.service.impl;

import com.limpiezait.dto.PedidoDto;
import com.limpiezait.dto.ProductoCarritoDto;
import com.limpiezait.entity.*;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.error.SinStockException;
import com.limpiezait.repository.ClienteRepository;
import com.limpiezait.repository.EstadoPedidoRepository;
import com.limpiezait.repository.PedidoRepository;
import com.limpiezait.service.interfaces.PedidoService;
import com.limpiezait.service.interfaces.ProductoPedidoService;
import com.limpiezait.service.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoService productoService;
    private final ProductoPedidoService productoPedidoService;





    //Actualizar Estado de pedido
    @Override
    public Pedido actualizarEstado(Long id, Long idEstado){
        //Buscamos el pedido por id
        Pedido pedido = pedidoRepository.findByActivoTrueAndId(id)
                .orElseThrow(()-> new ResourceNotFoundException("El pedido con el id "+ id+" no existe"));
        //Buscamos si el id del estado existe
        EstadoPedido ePedido =  estadoPedidoRepository.findById(idEstado)
                .orElseThrow(()->new ResourceNotFoundException("El estado id "+idEstado+" no existe"));
        //Si llegamos a este punto, debemos gestionar el cambio de estado
        pedido.setEstadoPedido(ePedido);
        //Retornamos el pedido actualizado
        return pedidoRepository.save(pedido);

    }


    @Override
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findByActivoTrue();
    }


    //Buscar Pedido por id
    @Override
    public Pedido buscarPorId(Long id) throws ResourceNotFoundException {
            //Devolvemos el "Pedido" o la exception
            return pedidoRepository.findByActivoTrueAndId(id)
                    .orElseThrow(()->new  ResourceNotFoundException("El pedido con el id "+id+" no existe"));
    }

    //Recibimos el DTO para crear el pedido
    @Override
    public Pedido crearPedido(PedidoDto pedidoDto) {
        //Buscamos el cliente o lanzamos exception
        Cliente cliente = clienteRepository.findByActivoTrueAndId(pedidoDto.getClienteId())
                .orElseThrow(()-> new ResourceNotFoundException("El cliente con el id "+pedidoDto.getClienteId()+" no existe"));
        //Buscamos el estado del pedido o lanzamos exception
        EstadoPedido estadoPedido = estadoPedidoRepository.findById(pedidoDto.getEstadoPedidoId())
                .orElseThrow(()->new ResourceNotFoundException("El estado con el id "+pedidoDto.getEstadoPedidoId()+" no existe"));
        //Creamos el pedido y seteamos cliente y estado
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstadoPedido(estadoPedido);


        return pedidoRepository.save(pedido);
    }


    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido)   {
        //LLamamos al "Pedido" o lanza exception desde el metodo de busqueda
        Pedido pedidoDB = buscarPorId(id);
        pedidoDB.setCliente(pedido.getCliente());
        pedidoDB.setProductos(pedido.getProductos());
        pedidoDB.setTotal(pedido.getTotal());
        pedidoRepository.save(pedidoDB);
        return pedidoDB;
    }

    @Override
    public Pedido actualizarProductoAlPedido(ProductoCarritoDto productoCarritoDto, Long idPedido) throws SinStockException {
        //Buscamos el pedido y el prodcuto. Si no se encuentra arroja exception;
        Producto producto = productoService.buscarPorId(productoCarritoDto.getIdProducto());
        Pedido pedido = buscarPorId(idPedido);

        if(producto.getStock()< productoCarritoDto.getCantidad()) {
            throw new SinStockException("No hay stock disponible para el producto "+ producto.getNombre());
        }


        productoPedidoService.actualizarCantidadProducto(producto, pedido, productoCarritoDto.getCantidad());



        //Actualizarmos valor del pedido
        //Obtenemos los productos del pedido en forma de lista
        List<ProductoPedido> productoPedidos = productoPedidoService.obtenerTodosDelPedido(idPedido);
        pedido.setTotal(BigDecimal.valueOf(0.0)); //Seteamos el total en 0
        //Recorremos la lista y vamos sumando los precios de cada producto
        for (ProductoPedido pp : productoPedidos){
            pedido.setTotal(pedido.getTotal().add(pp.getPrecioUnitario().multiply(BigDecimal.valueOf(pp.getCantidad()))));
        }

        //devolvemos el pedido
        return pedidoRepository.save(pedido);

    }


    @Override
    public void eliminarPedido(Long id){
        Pedido pedido = buscarPorId(id);
        pedido.setActivo(false);
        pedidoRepository.save(pedido);
        return;
    }


}


