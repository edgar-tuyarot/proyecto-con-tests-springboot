package com.limpiezait.service.interfaces;

import com.limpiezait.entity.Cliente;

import java.util.List;


public interface ClienteService {

    //Ver todos
    List<Cliente> obtenerTodos();
    //Guardar producto.
    Cliente crearCliente(Cliente cliente);
    //Buscar por ID:
    Cliente buscarPorId(Long id);
    //Actualizar:
    Cliente actualizar(Long id, Cliente cliente);
    //Eliminar producto:
    void desactivarCliente(Long id);



}
