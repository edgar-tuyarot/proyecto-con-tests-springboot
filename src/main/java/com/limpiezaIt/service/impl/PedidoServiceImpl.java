package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.repository.EstadoPedidoRepository;
import com.limpiezaIt.repository.PedidoRepository;
import com.limpiezaIt.service.interfaces.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, EstadoPedidoRepository estadoPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;

    }


    //Agregar estado de pedido desde EstadoPedido
    @Override
    public Optional<Pedido> actualizarEstado(Long id, Long idEstado) {

        //Buscamos si el id del estado existe
        Optional<EstadoPedido> estadoPedidoOpt = estadoPedidoRepository.findById(idEstado);
        //si no existe, retornamos un optiona vacio.
        if (!estadoPedidoOpt.isPresent()) return Optional.empty();

        //Buscamos el pedido por id
        Optional<Pedido> pedidoActualizado = pedidoRepository.findByActivoTrueAndId(id);
        //Si no existe, retornamos un optiona vacio.
        if (!pedidoActualizado.isPresent()) return Optional.empty();

        //Si llegamos a este punto, debemos gestionar el cambio de estado
        Pedido pedido = pedidoActualizado.get();
        pedido.setEstadoPedido(estadoPedidoOpt.get());
        pedidoRepository.save(pedido);

        //Retornamos el pedido actualizado
        return pedidoActualizado;


    }


    @Override
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findByActivoTrue();
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findByActivoTrueAndId(id);
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    @Override
    public Optional<Pedido> actualizarPedido(Long id, Pedido pedido) {
        Optional<Pedido> pedidoDB = buscarPorId(id);
        if (!pedidoDB.isPresent()) return Optional.empty();
        Pedido pedidoActualizado = pedidoDB.get();
        pedidoActualizado.setCliente(pedido.getCliente());
        pedidoActualizado.setProductos(pedido.getProductos());
        pedidoActualizado.setTotal(pedido.getTotal());
        pedidoRepository.save(pedidoActualizado);
        return Optional.of(pedidoActualizado);
    }

    @Override
    public boolean eliminarPedido(Long id) {
        Optional<Pedido> pedidoOpt = buscarPorId(id);
        if(pedidoOpt.isPresent()){
            Pedido pedido = pedidoOpt.get();
            pedido.setActivo(false);
            pedidoRepository.save(pedido);
            return true;
        }
        return false;
    }
}


