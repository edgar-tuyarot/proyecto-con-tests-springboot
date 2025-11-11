package com.productos.proyecto.repository;

import com.productos.proyecto.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    void testfindByNombreContainingIgnoreCase(){
        //Given
        Categoria categoria = Categoria.builder()
                .nombre("Test Cat")
                .descripcion("Categoria Test")
                .build();
        categoriaRepository.save(categoria);

        //When
        List<Categoria> resultado = categoriaRepository.findByNombreContainingIgnoreCase("test cat");

        //Then
        assertFalse(resultado.isEmpty());
        assertEquals("Test Cat", resultado.get(0).getNombre());

    }




}
