package com.polisport.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "instalacion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la instalación es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo de instalación es obligatorio")
    private String tipo;

    @Positive(message = "La capacidad debe ser un número positivo")
    private int capacidad;

    @NotNull(message = "Debe especificar si la instalación está disponible")
    private boolean disponible;

    @NotBlank(message = "El estado de la instalación es obligatorio")
    private String estado;
}