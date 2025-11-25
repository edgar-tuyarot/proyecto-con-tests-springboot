package com.limpiezaIt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estados_pedido")
@Builder
public class EstadoPedido {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)

    private String nombre;

    @OneToMany(mappedBy = "estadoPedido")
    @JsonIgnore   //Muy importante para evitar recursión infinita en JSON
    private List<Pedido> pedidos = new ArrayList<>();



}
