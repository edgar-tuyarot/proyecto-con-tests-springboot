package com.limpiezait.service.interfaces;
import com.limpiezait.entity.Producto;

import java.util.List;


public interface ProductoService {
    //Ver todos
    List<Producto> obtenerTodos();
    //Guardar producto.
    Producto guardarProducto(Producto producto);
    //Buscar por ID:
    Producto buscarPorId(Long id);
    //Actualizar:
    Producto actualizar(Long id, Producto producto);
    //Eliminar producto:
    void desactivarProducto(Long id);

}
