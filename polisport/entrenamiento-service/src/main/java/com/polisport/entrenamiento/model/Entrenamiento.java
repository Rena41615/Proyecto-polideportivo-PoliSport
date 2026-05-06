package com.polisport.entrenamiento.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "entrenamientos")
@Getter @Setter @ToString
@AllArgsConstructor@NoArgsConstructor

public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT del entrenador es obligatorio")
    @Column(Nullable = false, length = 12)
    private String rutEntrenador;

    private LocalDate fechaSesion;

    private TipoEntrenamiento tipoEntrenamiento;

    private Estado estado;

    private Integer duracionMinutos;

}
