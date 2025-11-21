package com.limpiezaIt.controller;

import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.service.interfaces.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<EstadoPedido> obtenerEstadoPedidoPorId(Long id) {
         Optional<EstadoPedido> estadoPedido = estadoPedidoService.verEstadoPedidoPorId(id);
        if (estadoPedido.isPresent()) {
            return ResponseEntity.ok(estadoPedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Crear un estado pedido
    @PostMapping
    public ResponseEntity<EstadoPedido> crearEstadoPedido(EstadoPedido estadoPedido) {
        try {
            EstadoPedido nuevoEstadoPedido = estadoPedidoService.guardarEstadoPedido(estadoPedido);
            return ResponseEntity.ok(nuevoEstadoPedido);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //Modificar un estado pedido
    @PutMapping("/{id}")
    public ResponseEntity<EstadoPedido> actualizarEstadoPedido(@PathVariable Long id, @RequestBody EstadoPedido estadoPedido) {
        EstadoPedido estadoPedidoActualizado = estadoPedidoService.actualizarEstadoPedido(id, estadoPedido);
        if (estadoPedidoActualizado != null) {
            return ResponseEntity.ok(estadoPedidoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    //Eliminar un estado pedido (borrado logico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadoPedido(@PathVariable Long id) {
        boolean resultado = estadoPedidoService.borrarEstadoPedido(id);
        if (resultado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
