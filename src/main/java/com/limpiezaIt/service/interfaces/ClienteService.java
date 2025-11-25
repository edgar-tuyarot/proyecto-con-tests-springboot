package com.limpiezaIt.service.interfaces;

import com.limpiezaIt.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    boolean desactivarCliente(Long id);



}
