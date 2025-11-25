package com.limpiezaIt.service.interfaces;
import com.limpiezaIt.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    boolean desactivarProducto(Long id);

}
