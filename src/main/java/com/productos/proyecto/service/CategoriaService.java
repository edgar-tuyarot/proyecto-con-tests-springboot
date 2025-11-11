package com.productos.proyecto.service;

import com.productos.proyecto.model.Categoria;
import com.productos.proyecto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Guardar o actualizar categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //Obtener todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    //Buscar categoría por ID
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }


    //Verificar si existe categoría por nombre
    public boolean existePorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }

    //Buscar categorías por nombre que contenga texto
    public List<Categoria> buscarPorNombreConteniendo(String texto) {
        return categoriaRepository.findByNombreContainingIgnoreCase(texto);
    }

    //Obtener categorías con productos activos
    public List<Categoria> obtenerCategoriasConProductosActivos() {
        return categoriaRepository.findCategoriasConProductosActivos();
    }

    //Obtener categorías sin productos
    public List<Categoria> obtenerCategoriasSinProductos() {
        return categoriaRepository.findCategoriasSinProductos();
    }

    //Eliminar categoría por ID
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    //Verificar si existe categoría por ID
    public boolean existeCategoria(Long id) {
        return categoriaRepository.existsById(id);
    }



}
