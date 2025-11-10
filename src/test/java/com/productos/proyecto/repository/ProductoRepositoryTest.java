package com.productos.proyecto.repository;

import com.productos.proyecto.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

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
                .build();

        // When - Ejecutar la acción
        Producto productoGuardado = productoRepository.save(producto);

        // Then - Verificar el resultado
        assertNotNull(productoGuardado.getId());
        assertEquals("Laptop Dell", productoGuardado.getNombre());
        assertEquals(1500.00, productoGuardado.getPrecio());
    }

    // Test para buscar por nombre
    @Test
    void testFindByNombre() {
        // Given
        Producto producto = Producto.builder()
                .nombre("Teclado Mecánico")
                .precio(89.99)
                .stock(25)
                .sku("TEC-MEC-001")
                .build();
        productoRepository.save(producto);

        // When
        Optional<Producto> resultado = productoRepository.findByNombre("Teclado Mecánico");

        // Then
        assertTrue(resultado.isPresent());
        assertEquals("Teclado Mecánico", resultado.get().getNombre());
    }

    // Test para buscar por nombre que contenga texto
    @Test
    void testFindByNombreContainingIgnoreCase() {
        // Given
        productoRepository.save(Producto.builder()
                .nombre("Mouse Inalámbrico")
                .precio(45.50)
                .stock(30)
                .sku("MOS-INL-001")
                .build());

        // When
        List<Producto> resultados = productoRepository.findByNombreContainingIgnoreCase("mouse");

        // Then
        assertFalse(resultados.isEmpty());
        assertEquals("Mouse Inalámbrico", resultados.get(0).getNombre());
    }

    // Test para tu método personalizado - buscar en nombre o descripción
    @Test
    void testFindByNombreContainingOrDescripcionContainingIgnoreCase() {
        // Given
        productoRepository.save(Producto.builder()
                .nombre("Monitor LG")
                .descripcion("Monitor gaming 144Hz")
                .precio(299.99)
                .stock(15)
                .sku("MON-LG-001")
                .build());

        // When - Buscar por texto que está en la descripción
        List<Producto> resultados = productoRepository.findByNombreContainingOrDescripcionContainingIgnoreCase("gaming","gaming");

        // Then
        assertFalse(resultados.isEmpty());
        assertEquals("Monitor LG", resultados.get(0).getNombre());
    }

    // Test para productos con precio mayor a
    @Test
    void testFindByPrecioGreaterThan() {
        // Given
        productoRepository.save(Producto.builder().nombre("Producto Barato").precio(50.0).stock(10).build());
        productoRepository.save(Producto.builder().nombre("Producto Caro").precio(200.0).stock(5).build());

        // When
        List<Producto> resultados = productoRepository.findByPrecioGreaterThan(100.0);

        // Then
        assertEquals(1, resultados.size());
        assertEquals("Producto Caro", resultados.get(0).getNombre());
    }
}
