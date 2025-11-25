package com.limpiezaIt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2,max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @Column(length = 11)
    @NotBlank(message = "El telefono es obligatorio")
    @Size(min = 6, max = 11, message = "El telefono debe tener entre 6 y 11 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo debe contener números")
    private String telefono;

    @Column(length = 13)
    @Size(min = 4, max = 12, message = "El celular debe entre 4 y 12 numeros")
    @Pattern(regexp = "^[0-9]*$", message = "El Celular solo debe contener números")
    private String celular;

    @Column(length = 50)
    @Email
    @Size(min = 4, max = 50, message = "El email debe tener entre 4 y 50 caracteres")
    private String email;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @OneToMany(mappedBy = "cliente")
    //Evitar recursión infinita en JSON
    @JsonIgnore
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

}
