package com.limpiezait.repository;

import com.limpiezait.entity.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Test para guardar un producto
    @Test
    void testGuardarProducto() {
        // Given - Configuración inicial
        Producto producto = Producto.builder()
                .nombre("Laptop Dell")
                .descripcion("Laptop gaming de 15 pulgadas")
                .precio(1500.00)
                .stock(10)
                .sku("LAP-DELL-001")
                .activo(true)
                .fechaCreacion(LocalDateTime.now())
                .build();

        // When - Ejecutar la acción
        Producto productoGuardado = productoRepository.save(producto);

        // Then - Verificar el resultado
        assertNotNull(productoGuardado.getId());
        assertEquals("Laptop Dell", productoGuardado.getNombre());
        assertEquals(1500.00, productoGuardado.getPrecio());
    }


    @Test
    void testDesactivarProducto(){

        // Given - Primero necesitámos un producto en la base de datos
        Producto producto = Producto.builder()
                .nombre("Producto Test")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();
        Producto productoGuardado = productoRepository.save(producto);
        Long productoId = productoGuardado.getId();

        // When - Actualizamos el estado a false
        productoRepository.desactivarProducto(productoId);
        entityManager.flush();
        entityManager.clear(); //Limpia el cache

        // Then - Verificamos que se actualizó utilizando entityManager, para actualizar la persistencia, ya que se usa @Query
        Optional<Producto> productoActualizado = Optional.ofNullable(entityManager.find(Producto.class, producto.getId()));

        assertTrue(productoActualizado.isPresent());
        assertFalse(productoActualizado.get().getActivo()); //Debería ser false ahora

    }


}
