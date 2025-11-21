package com.limpiezaIt.controller;


import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.service.interfaces.EstadoPedidoService;
import com.limpiezaIt.service.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);

        return pedido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable Long id,
            @RequestBody Pedido pedidoActualizado) {
        Optional<Pedido> pedido = pedidoService.actualizarPedido(id, pedidoActualizado);
        return pedido.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Modificar el estado de un pedido, recibimmos 2 ids
    @PutMapping("/{id}/estado/{idEstado}")
    public ResponseEntity<Pedido> agregarEstado(@PathVariable Long id, @PathVariable Long idEstado) {
        Optional<Pedido> pedido = pedidoService.actualizarEstado(id, idEstado);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        boolean resultado = pedidoService.eliminarPedido(id);
        if (resultado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
