package com.polisport.inventario.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "inventario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Inventario", description = "Equipo o material del complejo deportivo")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del inventario", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del articulo es obligatorio")
    @Column(name = "nombre", nullable = false)
    @Schema(name = "nombre", description = "Nombre del equipo o material", example = "Balón de fútbol")
    private String nombre;

    @Column(name = "descripcion")
    @Schema(name = "descripcion", description = "Descripción detallada del equipo o material", example = "Balón oficial de fútbol FIFA tamaño 5")
    private String descripcion;

    @Column(name = "ubicacion")
    @Schema(name = "ubicacion", description = "Ubicación donde se almacena el equipo", example = "Bodega B, Estante 3")
    private String ubicacion;

    @PositiveOrZero(message = "La cantidad no puede ser negativa")
    @Column(name = "cantidad")
    @Schema(name = "cantidad", description = "Cantidad disponible del artículo", example = "15")
    private int cantidad;

    @NotBlank(message = "El estado del articulo es obligatorio")
    @Column(name = "estado", nullable = false)
    @Schema(name = "estado", description = "Estado actual del equipo", example = "DISPONIBLE")
    private String estado;

    @NotNull(message = "El ID de la instalacion asociada es obligatorio")
    @Positive(message = "El ID de la instalacion debe ser un numero positivo")
    @Column(name = "instalacion_id", nullable = false)
    @Schema(name = "instalacionId", description = "ID de la instalación asociada al equipo", example = "5")
    private Long instalacionId;
}