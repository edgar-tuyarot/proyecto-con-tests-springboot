package com.productos.proyecto.controller;

import com.productos.proyecto.model.Producto;
import com.productos.proyecto.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void testObtenerTodos() throws Exception {
        // Given
        Producto p1 = Producto.builder()
                .nombre("Producto 1")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();
        Producto p2 = Producto.builder()
                .nombre("Producto 2")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();

        when(productoService.obtenerTodos()).thenReturn(Arrays.asList(p1, p2));

        // When & Then
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Producto 1"))
                .andExpect(jsonPath("$[1].nombre").value("Producto 2"));
    }

    @Test
    void testObtenerPorId_Existente() throws Exception {
        // Given
        Producto producto = Producto.builder()
                .nombre("Test Product")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();
        when(productoService.buscarPorId(1L)).thenReturn(Optional.of(producto));

        // When & Then
        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Test Product"))
                .andExpect(jsonPath("$.precio").value(100.0));
    }

    @Test
    void testObtenerPorId_NoExistente() throws Exception {
        // Given
        when(productoService.buscarPorId(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/productos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearProducto() throws Exception {
        // Given
        Producto productoMock = Producto.builder()
                .id(1L)
                .nombre("Nuevo Producto")
                .precio(150.0)
                .stock(5)
                .activo(true)
                .build();

        when(productoService.guardarProducto(any(Producto.class))).thenReturn(productoMock);

        // When & Then
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "nombre": "Nuevo Producto",
                    "precio": 150.0,
                    "stock": 5
                }
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())  // ← Verificar que tiene ID
                .andExpect(jsonPath("$.nombre").value("Nuevo Producto"));
    }

    @Test
    void testProductoPorCategorias() throws Exception {
        // Given - Configuración inicial
        Long categoriaId = 1L;

        // Crear productos mock para esa categoría
        Producto producto1 = Producto.builder()
                .id(1L)
                .nombre("Laptop Gaming")
                .precio(1500.0)
                .stock(5)
                .activo(true)
                .build();

        Producto producto2 = Producto.builder()
                .id(2L)
                .nombre("Mouse Inalámbrico")
                .precio(45.0)
                .stock(10)
                .activo(true)
                .build();

        List<Producto> productosMock = Arrays.asList(producto1, producto2);

        // Configurar el mock del service
        when(productoService.obtenerPorCategoria(categoriaId)).thenReturn(productosMock);

        // When & Then - Ejecutar el test
        mockMvc.perform(get("/api/productos/categoria/{id}", categoriaId))
                .andExpect(status().isOk())  // Verificar que responde 200 OK
                .andExpect(jsonPath("$.length()").value(2))  // Verificar que hay 2 productos
                .andExpect(jsonPath("$[0].nombre").value("Laptop Gaming"))  // Verificar primer producto
                .andExpect(jsonPath("$[1].nombre").value("Mouse Inalámbrico"));  // Verificar segundo producto
    }

}