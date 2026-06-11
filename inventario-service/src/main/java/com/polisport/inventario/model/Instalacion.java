package com.polisport.inventario.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "instalacion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Instalacion", description = "Instalación deportiva del complejo")
public class Instalacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único de la instalación", example = "1")
    private Long id;

    @NotBlank(message = "El nombre de la instalacion es obligatorio")
    @Column(name = "nombre", nullable = false)
    @Schema(name = "nombre", description = "Nombre de la instalación deportiva", example = "Cancha de Fútbol Principal")
    private String nombre;

    @Column(name = "descripcion")
    @Schema(name = "descripcion", description = "Descripción detallada de la instalación", example = "Cancha de fútbol 11 con iluminación nocturna")
    private String descripcion;

    @Column(name = "ubicacion")
    @Schema(name = "ubicacion", description = "Ubicación geográfica de la instalación", example = "Sector Norte del Complejo")
    private String ubicacion;

    @NotBlank(message = "El tipo de instalacion es obligatorio")
    @Column(name = "tipo", nullable = false)
    @Schema(name = "tipo", description = "Tipo de instalación deportiva", example = "Fútbol")
    private String tipo;

    @Positive(message = "La capacidad debe ser un numero positivo")
    @Column(name = "capacidad")
    @Schema(name = "capacidad", description = "Capacidad máxima de personas", example = "100")
    private int capacidad;

    @NotNull(message = "Debe especificar si la instalacion esta disponible")
    @Column(name = "disponible", nullable = false)
    @Schema(name = "disponible", description = "Indica si la instalación está disponible", example = "true")
    private boolean disponible;

    @NotBlank(message = "El estado de la instalacion es obligatorio")
    @Column(name = "estado", nullable = false)
    @Schema(name = "estado", description = "Estado actual de la instalación", example = "OPERATIVA")
    private String estado;
}