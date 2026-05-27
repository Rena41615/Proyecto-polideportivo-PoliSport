package com.polisport.entrenamiento.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "entrenamientos")
@Getter @Setter @ToString
@AllArgsConstructor@NoArgsConstructor

public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El RUN es obligatorio")
    @Positive(message = "El RUN debe ser valido")
    @Column(nullable = false)
    private Integer runEntrenador;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 caracter")
    @Column(nullable = false)
    private String dvrunEntrenador;

    @ElementCollection
    @CollectionTable(name = "asistencia_entrenamiento", joinColumns = @JoinColumn(name = "entrenamiento_id"))
    @Column(name = "atleta_run")
    @NotEmpty(message = "Debe haber al menos un atleta participando")
    private List<Integer> atletasParticipantes;

    @NotNull(message = "La fecha de la sesion es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @NotNull(message = "El tipo de entrenamiento no puede estar vacio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    private TipoEntrenamiento tipoEntrenamiento;

    @NotNull(message = "El estado del entrenamiento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    private EstadoEntrenamiento estado;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    private String observaciones;

    @Positive(message = "El nivel de intensidad debe ser positivo")
    private Double nivelIntensidad;

    @PositiveOrZero(message = "La duracion debe ser 0 o un numero positivo")
    @NotNull(message = "La duracion en minutos es obligatoria")
    private Integer duracionMinutos;
}