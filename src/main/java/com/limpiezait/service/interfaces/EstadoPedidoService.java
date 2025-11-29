package com.limpiezait.service.interfaces;

import com.limpiezait.entity.EstadoPedido;

import java.util.List;


public interface EstadoPedidoService {

    EstadoPedido guardarEstadoPedido(EstadoPedido estadoPedido);


    EstadoPedido verEstadoPedidoPorId(Long id);

    List<EstadoPedido> verTodosLosEstadosPedidos();

    void borrarEstadoPedido(Long id);



}
