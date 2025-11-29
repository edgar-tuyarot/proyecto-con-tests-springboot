package com.limpiezait.service.impl;


import com.limpiezait.entity.EstadoPedido;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.repository.EstadoPedidoRepository;
import com.limpiezait.service.interfaces.EstadoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EstadoPedidoImpl implements EstadoPedidoService {

    private final EstadoPedidoRepository estadoPedidoRepository;


    @Override
    public EstadoPedido guardarEstadoPedido(EstadoPedido estadoPedido) {
        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public EstadoPedido verEstadoPedidoPorId(Long id) throws ResourceNotFoundException {
        return estadoPedidoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El estado con el id "+id+" no existe"));
    }

    @Override
    public List<EstadoPedido> verTodosLosEstadosPedidos() {
        return estadoPedidoRepository.findAll();
    }

    @Override
    public void borrarEstadoPedido(Long id) {
        EstadoPedido ePedido = verEstadoPedidoPorId(id);
        estadoPedidoRepository.deleteById(id);
        return;
    }
}
