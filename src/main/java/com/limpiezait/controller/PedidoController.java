package com.limpiezait.controller;

import com.limpiezait.dto.PedidoDto;
import com.limpiezait.dto.ProductoCarritoDto;
import com.limpiezait.entity.Pedido;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.service.interfaces.EstadoPedidoService;
import com.limpiezait.service.interfaces.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {


    private  final PedidoService pedidoService;
    private final EstadoPedidoService estadoPedidoService;

    @GetMapping
    List<Pedido> obtenerTodos(){
        return pedidoService.obtenerTodos();
    }

   @GetMapping("/{id}")
   Pedido obtenerPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return pedidoService.buscarPorId(id);
    }

    //Actualizar Pedido
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    Pedido actualizarPedido(
            @PathVariable Long id,
            @RequestBody Pedido pedidoActualizado) throws ResourceNotFoundException {
        return pedidoService.actualizarPedido(id, pedidoActualizado);
    }

    //Modificar el estado de un pedido, recibimmos 2 ids
    @PutMapping("/{id}/estado/{idEstado}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    Pedido agregarEstado(@PathVariable Long id, @PathVariable Long idEstado) throws ResourceNotFoundException {
        return pedidoService.actualizarEstado(id, idEstado);
    }

    //Agregar Producto al pedido
    @PutMapping("/agregar-producto/{idPedido}")
    @PreAuthorize("hasAnyRole('ADMIN','VENDEDOR')")
    Pedido agregarProductoAlPedido(@PathVariable Long idPedido, @RequestBody ProductoCarritoDto productoCarritoDto){
        return pedidoService.actualizarProductoAlPedido(productoCarritoDto, idPedido);
    }

    //Crear Pedido
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','VENDEDOR')")
    Pedido crearPedido(@RequestBody PedidoDto pedidoDto){
            return pedidoService.crearPedido(pedidoDto);


    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) throws ResourceNotFoundException {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
