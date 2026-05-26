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
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El tipo de instalación es obligatorio")
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Positive(message = "La capacidad debe ser un número positivo")
    @Column(name = "capacidad")
    private int capacidad;

    @NotNull(message = "Debe especificar si la instalación está disponible")
    @Column(name = "disponible", nullable = false)
    private boolean disponible;

    @NotBlank(message = "El estado de la instalación es obligatorio")
    @Column(name = "estado", nullable = false)
    private String estado;
}