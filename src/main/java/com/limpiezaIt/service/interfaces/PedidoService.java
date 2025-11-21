package com.limpiezaIt.service.interfaces;

import com.limpiezaIt.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PedidoService {

    //Buscar todos
    List<Pedido> obtenerTodos();
    //Buscar pedido por id
    Optional<Pedido> buscarPorId(Long id);

    //Crear pedido
    Pedido crearPedido(Pedido pedido);

    //Agregar estado
    Optional<Pedido> actualizarEstado(Long id, Long idEstado);

    //Actualizar pedido
    Optional<Pedido> actualizarPedido(Long id, Pedido pedido);

    //Eliminar pedido
    boolean eliminarPedido(Long id);


}
