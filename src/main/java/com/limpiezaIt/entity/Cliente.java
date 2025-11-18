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
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String apellido;
    @Column(length = 11)
    private String telefono;
    @Column(length = 13)
    private String celular;
    @Column(length = 50)
    private String email;
    @Column(nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore   // MUY importante para evitar recursión infinita en JSON
    private List<Pedido> pedidos = new ArrayList<>();

}
