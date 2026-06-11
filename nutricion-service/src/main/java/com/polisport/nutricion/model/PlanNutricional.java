package com.polisport.nutricion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Getter
@Setter
@ToString(exclude = {"restricciones", "pautas", "suplementos"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planes_nutricionales")
@Schema(name = "PlanNutricional", description = "Plan nutricional personalizado de un atleta")
public class PlanNutricional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del plan nutricional", example = "1")
    private Long id;

    @Column(name = "atleta_id", nullable = false)
    @NotNull(message = "El Id del Atleta es obligatorio")
    @Schema(name = "atletaId", description = "ID único del atleta asociado", example = "42")
    private Long atletaId;

    @Column(name = "deporte", nullable = false, length = 100)
    @NotBlank(message = "El deporte es obligatorio")
    @Schema(name = "deporte", description = "Deporte que practica el atleta", example = "Fútbol")
    private String deporte;

    @Enumerated(EnumType.STRING)
    @Column(name = "objetivo", nullable = false, length = 30)
    @NotNull(message = "El objetivo nutricional es obligatorio")
    @Schema(name = "objetivo", description = "Objetivo nutricional del plan", example = "GANANCIA_MUSCULAR")
    private ObjetivoNutricional objetivo;

    @Column(name = "fecha_inicio")
    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    @Schema(name = "fechaInicio", description = "Fecha de inicio del plan", example = "2026-06-15")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @Future(message = "La fecha de fin debe ser en el futuro")
    @Schema(name = "fechaFin", description = "Fecha de finalización del plan", example = "2026-12-31")
    private LocalDate fechaFin;

    @Column(name = "calorias_diarias_gr")
    @NotNull(message = "Las calorias diarias son obligatorias")
    @Min(value = 1200, message = "Las calorias diarias deben ser al menos 1200")
    @Schema(name = "caloriasDiariasGr", description = "Calorías diarias recomendadas", example = "2500")
    private Integer caloriasDiariasGr;

    @Column(name = "proteina_gr")
    @NotNull(message = "Las proteinas diarias son obligatorias")
    @Min(value = 50, message = "Las proteinas diarias deben ser al menos 50 gramos")
    @Schema(name = "proteinaGr", description = "Proteína diaria recomendada en gramos", example = "150")
    private Integer proteinaGr;

    @Column(name = "carbohidratos_gr")
    @NotNull(message = "Los carbohidratos diarios son obligatorios")
    @Min(value = 45, message = "Los carbohidratos diarios deben ser al menos 45 gramos")
    @Schema(name = "carbohidratosGr", description = "Carbohidratos diarios recomendados en gramos", example = "300")
    private Integer carbohidratosGr;

    @Column(name = "lipidos_gr")
    @NotNull(message = "Los lipidos diarios son obligatorios")
    @Min(value = 30, message = "Los lipidos diarios deben ser al menos 30 gramos")
    @Schema(name = "lipidosGr", description = "Lípidos diarios recomendados en gramos", example = "80")
    private Integer lipidosGr;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Schema(name = "estado", description = "Estado actual del plan", example = "ACTIVO")
    private PlanEstado estado = PlanEstado.BORRADOR;

    @Column(name = "notas", length = 500)
    @Schema(name = "notas", description = "Notas o comentarios adicionales del plan", example = "Atleta con sensibilidad al gluten, evitar cereales")
    private String notas;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(name = "restricciones", description = "Restricciones alimentarias del atleta", example = "[]")
    private List<RestriccionAlimentaria> restricciones = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(name = "pautas", description = "Pautas alimentarias del plan", example = "[]")
    private List<PautaAlimentaria> pautas = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(name = "suplementos", description = "Suplementos nutricionales del plan", example = "[]")
    private List<Suplementacion> suplementos = new ArrayList<>();
}
