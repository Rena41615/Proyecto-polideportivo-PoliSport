package com.polisport.entrenamiento.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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
    @Positive(message = "El RUN debe ser válido")
    @Column(nullable = false)
    private Integer runEntrenador;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 carácter")
    @Column(nullable = false)
    private String dvrunEntrenador;

    @ElementCollection
    @CollectionTable(name = "asistencia_entrenamiento", joinColumns = @JoinColumn(name = "entrenamiento_id"))
    @Column(name = "atleta_run")
    @NotEmpty(message = "Debe haber al menos un atleta participando")
    private List<Integer> participantesRun;

    @NotNull(message = "La fecha de la sesion es obligatoria")
    private LocalDate fechaSesion;

    @NotNull(message = "El tipo de entrenamiento no puede estar vacío")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    private TipoEntrenamiento tipoEntrenamiento;

    @NotNull(message = "El estado del entrenamiento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    private EstadoEntrenamiento estado;

    @PositiveOrZero(message = "La duración debe ser 0 o un número positivo")
    @NotNull(message = "La duración en minutos es obligatoria")
    private Integer duracionMinutos;
}