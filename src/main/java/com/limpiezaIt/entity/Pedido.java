package com.limpiezaIt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column
    private double total;

    @Column
    private boolean activo = true;

    //Definimos que la relacion sera un pedido tiene un estado
    @ManyToOne
    @JoinColumn(name = "estado_pedido_id")
    private EstadoPedido estadoPedido;

    //Definimos que la relacion sera un pedido pertenece a un cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //Definimos la relacion uno a muchos con ProductoPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProductoPedido> productos;


}
