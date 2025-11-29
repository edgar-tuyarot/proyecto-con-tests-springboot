package com.limpiezait.service.impl;

import com.limpiezait.entity.Categoria;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.repository.CategoriaRepository;
import com.limpiezait.service.interfaces.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    //Guardar o actualizar categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //Obtener todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findByActivoTrue();
    }

    //Buscar categoría por ID
    public Categoria buscarPorId(Long id) throws ResourceNotFoundException {
        return categoriaRepository.findByActivoTrueAndId(id)
                .orElseThrow(()->new ResourceNotFoundException("Categoria con id "+id+" no encontrada"));
    }


    //Modificar categoría
    public Categoria actualizar(Long id, Categoria categoria) {
        //verificamos que la categoría esté en DB
        Categoria categoriaDB = buscarPorId(id);
        categoriaDB.setNombre(categoria.getNombre());
        categoriaDB.setDescripcion(categoria.getDescripcion());
        //Se guarda
            return categoriaRepository.save(categoriaDB);
    }

    //Borrado logico de categoría
    public void desactivarCategoria(Long id) {
        Categoria categoria = buscarPorId(id);
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
        return;
    }

}
