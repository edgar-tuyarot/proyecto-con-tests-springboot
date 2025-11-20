package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.repository.PedidoRepository;
import com.limpiezaIt.service.interfaces.PedidoService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
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
    public Pedido actualizarPedido(Long id, Pedido pedido) {

        Optional<Pedido> pedidoDB = buscarPorId(id);
        if(pedidoDB.isPresent()){
            Pedido pedidoActualizado = pedidoDB.get();
            pedidoActualizado.setCliente(pedido.getCliente());
            pedidoActualizado.setProductos(pedido.getProductos());
            pedidoActualizado.setTotal(pedido.getTotal());
            return pedidoRepository.save(pedidoActualizado);
        }

        return null;
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
