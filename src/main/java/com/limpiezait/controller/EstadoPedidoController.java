package com.limpiezait.controller;

import com.limpiezait.entity.EstadoPedido;
import com.limpiezait.service.interfaces.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado-pedidos")
public class EstadoPedidoController {

    @Autowired
    EstadoPedidoService estadoPedidoService;

    //Obtener todos los estados de pedido
    @GetMapping
    public ResponseEntity<List<EstadoPedido>> obtenerTodosLosEstadosPedidos() {
        List<EstadoPedido> estadosPedidos = estadoPedidoService.verTodosLosEstadosPedidos();
        return ResponseEntity.ok(estadosPedidos);
    }

    //Obtener un estado pedido por id
    @GetMapping("/{id}")
    EstadoPedido obtenerEstadoPedidoPorId(Long id) {
        return estadoPedidoService.verEstadoPedidoPorId(id);
    }

    //Crear un estado pedido
    @PostMapping
    public ResponseEntity<EstadoPedido> crearEstadoPedido(@RequestBody EstadoPedido estadoPedido) {
            EstadoPedido nuevoEstadoPedido = estadoPedidoService.guardarEstadoPedido(estadoPedido);
            return ResponseEntity.ok(nuevoEstadoPedido);

    }

    //Eliminar un estado pedido (borrado logico)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> eliminarEstadoPedido(@PathVariable Long id) {
        estadoPedidoService.borrarEstadoPedido(id);
        return ResponseEntity.noContent().build();
    }

}
