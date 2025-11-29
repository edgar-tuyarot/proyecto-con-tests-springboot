package com.limpiezait.service.interfaces;

import com.limpiezait.entity.Categoria;

import java.util.List;


public interface CategoriaService {

    //Ver todas
    List<Categoria> obtenerTodas();
    //Guardar categoria.
    Categoria guardarCategoria(Categoria categoria);
    //Buscar por ID:
    Categoria buscarPorId(Long id);
    //Actualizar:
    Categoria actualizar(Long id, Categoria categoria);
    //Eliminar categoria:
    void desactivarCategoria(Long id);


}
