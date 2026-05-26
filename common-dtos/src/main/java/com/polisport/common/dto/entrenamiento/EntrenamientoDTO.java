package com.polisport.common.dto.entrenamiento;

import com.polisport.entrenamiento.model.TipoEntrenamiento;
import com.polisport.entrenamiento.model.EstadoEntrenamiento;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class EntrenamientoDTO {

    private Long id;
    private TipoEntrenamiento tipoEntrenamiento;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EstadoEntrenamiento estado;
    private List<Integer> atletasParticipantes;
    private String observaciones;
    private Double nivelIntensidad;
}
