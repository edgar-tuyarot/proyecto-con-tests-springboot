package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.error.ResourceNotFoundException;
import com.limpiezaIt.repository.EstadoPedidoRepository;
import com.limpiezaIt.repository.PedidoRepository;
import com.limpiezaIt.service.interfaces.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, EstadoPedidoRepository estadoPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;

    }


    //Actualizar Estado de pedido
    @Override
    public Pedido actualizarEstado(Long id, Long idEstado){

        //Buscamos el pedido por id
        Pedido pedido = pedidoRepository.findByActivoTrueAndId(id)
                .orElseThrow(()-> new ResourceNotFoundException("El pedido con el id "+ id+" no existe"));

        //Buscamos si el id del estado existe
        EstadoPedido ePedido =  estadoPedidoRepository.findById(idEstado)
                .orElseThrow(()->new ResourceNotFoundException("El estado id "+idEstado+" no existe"));



        //Si llegamos a este punto, debemos gestionar el cambio de estado
        pedido.setEstadoPedido(ePedido);

        //Retornamos el pedido actualizado
        return pedidoRepository.save(pedido);


    }


    @Override
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findByActivoTrue();
    }

    @Override
    public Pedido buscarPorId(Long id) throws ResourceNotFoundException {
                Optional<Pedido> optionalPedido =  pedidoRepository.findByActivoTrueAndId(id);
                if (!optionalPedido.isPresent()){
                    throw new ResourceNotFoundException("El Pedido con el id "+id+" no existe");
                }
                return optionalPedido.get();
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido) throws ResourceNotFoundException {

        Pedido pedidoDB = pedidoRepository.findByActivoTrueAndId(id)
                .orElseThrow(()-> new ResourceNotFoundException("El pedido con el id "+ id+" no existe"));

        pedidoDB.setCliente(pedido.getCliente());
        pedidoDB.setProductos(pedido.getProductos());
        pedidoDB.setTotal(pedido.getTotal());
        pedidoRepository.save(pedidoDB);
        return pedidoDB;
    }

    @Override
    public boolean eliminarPedido(Long id) throws ResourceNotFoundException {
        Pedido pedidoOpt = pedidoRepository.findByActivoTrueAndId(id)
                .orElseThrow(()-> new ResourceNotFoundException("El pedido con el id "+ id+" no existe"));
         pedidoOpt.setActivo(false);
            pedidoRepository.save(pedidoOpt);
            return true;
        }


}


