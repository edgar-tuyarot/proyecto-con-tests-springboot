package com.limpiezaIt.service.interfaces;

import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.error.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PedidoService {

    //Buscar todos
    List<Pedido> obtenerTodos();
    //Buscar pedido por id
    Pedido buscarPorId(Long id) throws ResourceNotFoundException;

    //Crear pedido
    Pedido crearPedido(Pedido pedido);

    //Agregar estado
    Pedido actualizarEstado(Long id, Long idEstado);

    //Actualizar pedido
    Pedido actualizarPedido(Long id, Pedido pedido);

    //Eliminar pedido
    boolean eliminarPedido(Long id);


}
