package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.Categoria;
import com.limpiezaIt.repository.CategoriaRepository;
import com.limpiezaIt.service.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    //Guardar o actualizar categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //Obtener todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findByActivoTrue();
    }

    //Buscar categoría por ID
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findByActivoTrueAndId(id);
    }


    //Modificar categoría
    public Categoria actualizar(Long id, Categoria categoria) {
        //verificamos que la categoría esté en DB
        Optional<Categoria> opt = buscarPorId(id);
        //Si está, se pasa a actualizar
        if (opt.isPresent()) {
            Categoria categoriaDB = opt.get();
            categoriaDB.setNombre(categoria.getNombre());
            categoriaDB.setDescripcion(categoria.getDescripcion());
            //Se guarda
            return categoriaRepository.save(categoriaDB);
        } else {
            //Se retorna null si no está.
            return null;
        }
    }

    //Borrado logico de categoría
    public boolean desactivarCategoria(Long id) {
        Optional<Categoria> opt = buscarPorId(id);
        if (opt.isPresent()) {
            Categoria categoria = opt.get();
            categoria.setActivo(false);
            categoriaRepository.save(categoria);
            return true;
        }
        return false;
    }
}
