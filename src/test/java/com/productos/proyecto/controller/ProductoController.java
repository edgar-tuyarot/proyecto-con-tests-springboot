package com.productos.proyecto.controller;

import com.productos.proyecto.model.Producto;
import com.productos.proyecto.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
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
        Producto p1 = new Producto(1L, "Producto 1", "Desc 1", 100.0, 10, "SKU1");
        Producto p2 = new Producto(2L, "Producto 2", "Desc 2", 200.0, 20, "SKU2");

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
        Producto producto = new Producto(1L, "Test", "Desc", 100.0, 10, "SKU");
        when(productoService.buscarPorId(1L)).thenReturn(Optional.of(producto));

        // When & Then
        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Test"))
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
        Producto producto = new Producto(1L, "Nuevo", "Desc", 150.0, 5, "SKU-NEW");
        when(productoService.guardarProducto(any(Producto.class))).thenReturn(producto);

        // When & Then
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "nombre": "Nuevo",
                        "descripcion": "Desc",
                        "precio": 150.0,
                        "stock": 5,
                        "sku": "SKU-NEW"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Nuevo"));
    }
}