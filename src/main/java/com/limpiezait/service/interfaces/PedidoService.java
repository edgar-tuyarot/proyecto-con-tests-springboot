package com.limpiezait.service.interfaces;

import com.limpiezait.dto.PedidoDto;
import com.limpiezait.entity.Pedido;
import com.limpiezait.error.ResourceNotFoundException;

import java.util.List;


public interface PedidoService {

    //Buscar todos
    List<Pedido> obtenerTodos();
    //Buscar pedido por id
    Pedido buscarPorId(Long id) throws ResourceNotFoundException;

    //Crear pedido
    Pedido crearPedido(PedidoDto pedidoDto);

    //Agregar estado
    Pedido actualizarEstado(Long id, Long idEstado);

    //Actualizar pedido
    Pedido actualizarPedido(Long id, Pedido pedido);

    //Agregar producto al pedido
    Pedido agregarProductoAlPedido(Long id, Long idProducto);

    //Eliminar pedido
    void eliminarPedido(Long id);


}
