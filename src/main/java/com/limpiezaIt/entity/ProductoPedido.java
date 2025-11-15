package com.limpiezaIt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoPedido {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int cantidad;

    @Column
    private double precioUnitario;

    //Definimos la relacion muchos a uno con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    //Definimos la relacion muchos a uno con Pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
