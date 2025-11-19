package com.limpiezaIt.service.interfaces;

import com.limpiezaIt.entity.EstadoPedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstadoPedidoService {

    EstadoPedido guardarEstadoPedido(EstadoPedido estadoPedido);

    EstadoPedido actualizarEstadoPedido(Long id, EstadoPedido estadoPedido);

    EstadoPedido verEstadoPedidoPorId(Long id);

    List<EstadoPedido> verTodosLosEstadosPedidos();

    boolean borrarEstadoPedido(Long id);



}
