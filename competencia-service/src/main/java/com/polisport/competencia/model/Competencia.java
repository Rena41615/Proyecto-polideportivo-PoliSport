package com.polisport.competencia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "competencias")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Schema(name = "Competencia", description = "Registro de una competencia deportiva")
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único de la competencia", example = "1")
    private Long id;

    @NotBlank(message = "Nombre de la competencia es un campo obligatorio")
    @Column(nullable = false)
    @Schema(name = "nombreCompetencia", description = "Nombre de la competencia", example = "Campeonato Nacional de Atletismo 2026")
    private String nombreCompetencia;

    @NotBlank(message = "El lugar es un campo obligatorio")
    @Schema(name = "lugarCompetencia", description = "Lugar donde se realiza la competencia", example = "Estadio Nacional, Santiago")
    private String lugarCompetencia;

    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    @Schema(name = "fechaInicio", description = "Fecha de inicio de la competencia", example = "2026-07-15")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es un campo obligatorio")
    @Schema(name = "fechaFin", description = "Fecha de fin de la competencia", example = "2026-07-17")
    private LocalDate fechaFin;

    @NotNull(message = "La categoria es un campo obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "categoria", description = "Categoría de la competencia", example = "JUNIOR")
    private Categoria categoria;

    @NotNull(message = "La modalidad es un campo obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "modalidad", description = "Modalidad de la competencia", example = "INDIVIDUAL")
    private Modalidad modalidad;

    @NotNull(message = "El estado es un campo obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "estadoCompetencia", description = "Estado actual de la competencia", example = "PROGRAMADA")
    private EstadoCompetencia estadoCompetencia;

    @ElementCollection
    @CollectionTable(name = "inscritos_competencia", joinColumns = @JoinColumn(name = "competencia_id"))
    @Column(name = "atleta_run")
    @Schema(name = "inscritosRun", description = "Lista de RUNs de atletas inscritos", example = "[20456789, 21567890, 22678901]")
    private List<Integer> inscritosRun;

    @Size(max = 2000)
    @Column(length = 2000)
    @Schema(name = "resultadosDetalle", description = "Detalles de los resultados de la competencia", example = "1. Juan Pérez - 11.23s, 2. Carlos García - 11.45s, 3. Andrés López - 11.67s")
    private String resultadosDetalle;

}