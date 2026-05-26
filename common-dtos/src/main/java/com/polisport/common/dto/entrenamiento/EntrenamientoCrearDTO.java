package com.polisport.common.dto.entrenamiento;

import com.polisport.entrenamiento.model.TipoEntrenamiento;
import com.polisport.entrenamiento.model.EstadoEntrenamiento;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class EntrenamientoCrearDTO {

    @NotNull(message = "El tipo de entrenamiento es obligatorio")
    private TipoEntrenamiento tipoEntrenamiento;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @NotNull(message = "El estado es obligatorio")
    private EstadoEntrenamiento estado;

    @NotEmpty(message = "Debe haber al menos un participante")
    private List<Integer> atletasParticipantes;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    private String observaciones;

    @Positive(message = "El nivel de intensidad debe ser positivo")
    private Double nivelIntensidad;
}
