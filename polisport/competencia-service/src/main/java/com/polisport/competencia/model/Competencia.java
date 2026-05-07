package com.polisport.competencia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "competencias")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor

public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre de la competencia es un campo obligatorio")
    @Column(nullable = false)
    private String nombreCompetencia;

    @NotBlank(message = "El lugar es un campo obligatorio")
    private String lugarCompetencia;

    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es un campo obligatorio")
    private LocalDate fechaFin;

    @NotNull(message = "La categoría es un campo obligatorio")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotNull(message = "La modalidad es un campo obligatorio")
    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;

    @NotNull(message = "El estado es un campo obligatorio")
    @Enumerated(EnumType.STRING)
    private EstadoCompetencia estadoCompetencia;

    @ElementCollection
    @CollectionTable(name = "inscritos_competencia", joinColumns = @JoinColumn(name = "competencia_id"))
    @Column(name = "atleta_run")
    private List<Integer> inscritosRun;

    @Size(max = 2000)
    @Column(length = 2000)
    private String resultadosDetalle;

}