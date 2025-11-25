package com.limpiezaIt.service.impl;


import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.error.ResourceNotFoundException;
import com.limpiezaIt.repository.EstadoPedidoRepository;
import com.limpiezaIt.service.interfaces.EstadoPedidoService;
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
    public boolean borrarEstadoPedido(Long id) {
        Optional<EstadoPedido> estadoPedidoOpt = estadoPedidoRepository.findById(id);
        if(estadoPedidoOpt.isEmpty()){
            return false;
        }
        estadoPedidoRepository.deleteById(id);
        return true;
    }
}
