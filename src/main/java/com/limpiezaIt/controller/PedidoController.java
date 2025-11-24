package com.limpiezaIt.controller;

import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.error.ResourceNotFoundException;
import com.limpiezaIt.service.interfaces.EstadoPedidoService;
import com.limpiezaIt.service.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {


    private  final PedidoService pedidoService;
    private final EstadoPedidoService estadoPedidoService;

    @Autowired
    public PedidoController( PedidoService pedidoService,  EstadoPedidoService estadoPedidoService){
        this.pedidoService = pedidoService;
        this.estadoPedidoService = estadoPedidoService;
    }


    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos(){
            List<Pedido> pedidos = pedidoService.obtenerTodos();
            //Devolvemos la lista de pedidos o una lista vacia.
            return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
   Pedido obtenerPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    Pedido actualizarPedido(
            @PathVariable Long id,
            @RequestBody Pedido pedidoActualizado) throws ResourceNotFoundException {
        return pedidoService.actualizarPedido(id, pedidoActualizado);
    }

    //Modificar el estado de un pedido, recibimmos 2 ids
    @PutMapping("/{id}/estado/{idEstado}")
    Pedido agregarEstado(@PathVariable Long id, @PathVariable Long idEstado) throws ResourceNotFoundException {
        return pedidoService.actualizarEstado(id, idEstado);
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido){
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(pedido);
            return ResponseEntity.status(201).body(nuevoPedido);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) throws ResourceNotFoundException {
        boolean resultado = pedidoService.eliminarPedido(id);
        if (resultado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
