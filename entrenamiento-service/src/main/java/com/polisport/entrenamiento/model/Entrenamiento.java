package com.polisport.entrenamiento.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "entrenamientos")
@Getter @Setter @ToString
@AllArgsConstructor@NoArgsConstructor
@Schema(name = "Entrenamiento", description = "Sesión de entrenamiento de atletas")
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único de la sesión de entrenamiento", example = "1")
    private Long id;

    @NotNull(message = "El RUN es obligatorio")
    @Positive(message = "El RUN debe ser valido")
    @Column(nullable = false)
    @Schema(name = "runEntrenador", description = "RUN del entrenador sin dígito verificador", example = "15234789")
    private Integer runEntrenador;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 caracter")
    @Column(nullable = false)
    @Schema(name = "dvrunEntrenador", description = "Dígito verificador del RUN del entrenador", example = "2")
    private String dvrunEntrenador;

    @ElementCollection
    @CollectionTable(name = "asistencia_entrenamiento", joinColumns = @JoinColumn(name = "entrenamiento_id"))
    @Column(name = "atleta_run")
    @NotEmpty(message = "Debe haber al menos un atleta participando")
    @Schema(name = "atletasParticipantes", description = "Lista de RUNs de atletas participantes", example = "[19876543, 20123456, 21654321]")
    private List<Integer> atletasParticipantes;

    @NotNull(message = "La fecha de la sesion es obligatoria")
    @Schema(name = "fecha", description = "Fecha de la sesión de entrenamiento", example = "2025-06-15")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Schema(name = "horaInicio", description = "Hora de inicio de la sesión", example = "08:00:00")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @Schema(name = "horaFin", description = "Hora de fin de la sesión", example = "10:00:00")
    private LocalTime horaFin;

    @NotNull(message = "El tipo de entrenamiento no puede estar vacio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "tipoEntrenamiento", description = "Tipo de entrenamiento", example = "TACTICO")
    private TipoEntrenamiento tipoEntrenamiento;

    @NotNull(message = "El estado del entrenamiento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "estado", description = "Estado de la sesión de entrenamiento", example = "COMPLETADO")
    private EstadoEntrenamiento estado;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    @Schema(name = "observaciones", description = "Observaciones adicionales sobre la sesión", example = "Sesión exitosa, todos los atletas completaron los ejercicios")
    private String observaciones;

    @Positive(message = "El nivel de intensidad debe ser positivo")
    @Schema(name = "nivelIntensidad", description = "Nivel de intensidad del entrenamiento del 1 al 10", example = "7.5")
    private Double nivelIntensidad;

    @PositiveOrZero(message = "La duracion debe ser 0 o un numero positivo")
    @NotNull(message = "La duracion en minutos es obligatoria")
    @Schema(name = "duracionMinutos", description = "Duración de la sesión en minutos", example = "120")
    private Integer duracionMinutos;
}