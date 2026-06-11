package com.polisport.nutricion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suplementaciones")
@Schema(name = "Suplementacion", description = "Suplementación nutricional asociada a un plan")
public class Suplementacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único de la suplementación", example = "1")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    @NotNull(message = "El tipo de suplemento es obligatorio")
    @Schema(name = "tipo", description = "Tipo de suplemento", example = "PROTEINA")
    private TipoSuplemento tipo;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre del suplemento es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(name = "nombre", description = "Nombre comercial del suplemento", example = "Whey Protein Isolate")
    private String nombre;

    @Column(name = "dosis")
    @Min(value = 1, message = "La dosis debe ser mayor a 0")
    @Schema(name = "dosis", description = "Dosis de consumo", example = "25")
    private Integer dosis;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad", length = 20)
    @Schema(name = "unidad", description = "Unidad de medida", example = "GRAMOS")
    private UnidadMedida unidad;

    @Column(name = "horario")
    @Schema(name = "horario", description = "Horario de consumo del suplemento", example = "07:00:00")
    private LocalTime horario;

    @Column(name = "observaciones", length = 300)
    @Size(max = 300, message = "Las observaciones no pueden exceder 300 caracteres")
    @Schema(name = "observaciones", description = "Observaciones o notas adicionales", example = "Tomar con agua fría después del entrenamiento")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @NotNull(message = "El plan nutricional es obligatorio")
    @JsonIgnoreProperties("suplementos")
    @Schema(name = "plan", description = "Plan nutricional asociado", example = "{\"id\": 1}")
    private PlanNutricional plan;
}
