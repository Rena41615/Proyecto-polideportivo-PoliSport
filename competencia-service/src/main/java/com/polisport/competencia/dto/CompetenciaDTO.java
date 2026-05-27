package com.polisport.competencia.dto;

import com.polisport.competencia.model.Categoria;
import com.polisport.competencia.model.Modalidad;
import com.polisport.competencia.model.EstadoCompetencia;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CompetenciaDTO {

    private Long id;
    private String nombreCompetencia;
    private String lugarCompetencia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Categoria categoria;
    private Modalidad modalidad;
    private EstadoCompetencia estadoCompetencia;
    private List<Integer> inscritosRun;
    private String resultadosDetalle;
}
