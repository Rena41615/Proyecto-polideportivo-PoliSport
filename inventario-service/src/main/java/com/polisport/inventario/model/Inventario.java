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

    @NotBlank(message = "El nombre del articulo es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @PositiveOrZero(message = "La cantidad no puede ser negativa")
    @Column(name = "cantidad")
    private int cantidad;

    @NotBlank(message = "El estado del articulo es obligatorio")
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull(message = "El ID de la instalacion asociada es obligatorio")
    @Positive(message = "El ID de la instalacion debe ser un numero positivo")
    @Column(name = "instalacion_id", nullable = false)
    private Long instalacionId;
}