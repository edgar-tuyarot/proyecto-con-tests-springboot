package com.limpiezaIt.service.interfaces;

import com.limpiezaIt.entity.EstadoPedido;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface EstadoPedidoService {

    EstadoPedido guardarEstadoPedido(EstadoPedido estadoPedido);

    Optional<EstadoPedido> actualizarEstadoPedido(Long id, EstadoPedido estadoPedido);

    Optional<EstadoPedido> verEstadoPedidoPorId(Long id);

    List<EstadoPedido> verTodosLosEstadosPedidos();

    boolean borrarEstadoPedido(Long id);



}
