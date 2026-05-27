package com.polisport.nutricion.dto;

import com.polisport.common.model.ObjetivoNutricional;
import com.polisport.common.model.PlanEstado;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PlanNutricionalDTO {

    private Long id;
    private Long atletaId;
    private ObjetivoNutricional objetivo;
    private PlanEstado estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer caloriasDiarias;
    private String observaciones;
}
