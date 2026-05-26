package com.polisport.common.dto.competencia;

import com.polisport.competencia.model.Categoria;
import com.polisport.competencia.model.Modalidad;
import com.polisport.competencia.model.EstadoCompetencia;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CompetenciaCrearDTO {

    @NotBlank(message = "El nombre de la competencia es obligatorio")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    private String nombreCompetencia;

    @NotBlank(message = "El lugar de la competencia es obligatorio")
    @Size(min = 3, max = 255, message = "El lugar debe tener entre 3 y 255 caracteres")
    private String lugarCompetencia;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @FutureOrPresent(message = "La fecha de fin debe ser hoy o en el futuro")
    private LocalDate fechaFin;

    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;

    @NotNull(message = "La modalidad es obligatoria")
    private Modalidad modalidad;

    @NotNull(message = "El estado es obligatorio")
    private EstadoCompetencia estadoCompetencia;

    private List<Integer> inscritosRun;

    @Size(max = 2000, message = "Los resultados no pueden exceder 2000 caracteres")
    private String resultadosDetalle;
}
