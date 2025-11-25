package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.Categoria;
import com.limpiezaIt.error.ResourceNotFoundException;
import com.limpiezaIt.repository.CategoriaRepository;
import com.limpiezaIt.service.interfaces.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public boolean desactivarCategoria(Long id) {
        Categoria categoria = buscarPorId(id);
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
        return true;

    }
}
