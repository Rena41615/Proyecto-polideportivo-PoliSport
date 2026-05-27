package com.polisport.nutricion.dto;

import com.polisport.common.model.ObjetivoNutricional;
import com.polisport.common.model.PlanEstado;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PlanNutricionalCrearDTO {

    @NotNull(message = "El ID del atleta es obligatorio")
    @Positive(message = "El ID del atleta debe ser positivo")
    private Long atletaId;

    @NotNull(message = "El objetivo nutricional es obligatorio")
    private ObjetivoNutricional objetivo;

    @NotNull(message = "El estado del plan es obligatorio")
    private PlanEstado estado;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @Min(value = 1000, message = "Las calorías diarias deben ser mayor a 1000")
    @Max(value = 5000, message = "Las calorías diarias no pueden ser mayor a 5000")
    private Integer caloriasDiarias;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    private String observaciones;
}
