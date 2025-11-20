package com.limpiezaIt.service.interfaces;

import com.limpiezaIt.entity.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CategoriaService {

    //Ver todas
    List<Categoria> obtenerTodas();
    //Guardar categoria.
    Categoria guardarCategoria(Categoria categoria);
    //Buscar por ID:
    Optional<Categoria> buscarPorId(Long id);
    //Actualizar:
    Categoria actualizar(Long id, Categoria categoria);
    //Eliminar categoria:
    boolean desactivarCategoria(Long id);


}
