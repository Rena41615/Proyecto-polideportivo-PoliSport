package com.polisport.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Table(name = "inventario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del artículo es obligatorio")
    private String nombre;

    @PositiveOrZero(message = "La cantidad no puede ser negativa")
    private int cantidad;

    @NotBlank(message = "El estado del artículo es obligatorio")
    private String estado;

    @NotNull(message = "El ID de la instalación asociada es obligatorio")
    @Positive(message = "El ID de la instalación debe ser un número positivo")
    private Long instalacionId;
}