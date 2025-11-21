package com.limpiezaIt.service.impl;


import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.repository.EstadoPedidoRepository;
import com.limpiezaIt.service.interfaces.EstadoPedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EstadoPedidoImpl implements EstadoPedidoService {

    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoImpl(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }


    @Override
    public EstadoPedido guardarEstadoPedido(EstadoPedido estadoPedido) {
        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public Optional<EstadoPedido> actualizarEstadoPedido(Long id, EstadoPedido estadoPedido) {
        Optional<EstadoPedido> estadoPedidoDB = verEstadoPedidoPorId(id);
        if(estadoPedidoDB.isPresent()){
            estadoPedidoDB.get().setNombre(estadoPedido.getNombre());
            estadoPedidoRepository.save(estadoPedidoDB.get());
            return estadoPedidoDB;
        }
        return Optional.empty();
    }

    @Override
    public Optional<EstadoPedido> verEstadoPedidoPorId(Long id) {
        Optional<EstadoPedido> estadoPedidoOpt = estadoPedidoRepository.findById(id);
        if(estadoPedidoOpt.isPresent()){
            return estadoPedidoOpt;
        }
        return Optional.empty();
    }

    @Override
    public List<EstadoPedido> verTodosLosEstadosPedidos() {
        return estadoPedidoRepository.findAll();
    }

    @Override
    public boolean borrarEstadoPedido(Long id) {
        Optional<EstadoPedido> estadoPedidoOpt = estadoPedidoRepository.findById(id);
        if(estadoPedidoOpt.isEmpty()){
            return false;
        }
        estadoPedidoRepository.deleteById(id);
        return true;
    }
}
