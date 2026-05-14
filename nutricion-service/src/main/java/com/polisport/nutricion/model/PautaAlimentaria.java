package com.polisport.nutricion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pautas_alimentarias")

public class PautaAlimentaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 30)
    @NotNull(message = "La categoría de comida es obligatoria")
    private CategoriaComida categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 10)
    @NotNull(message = "El día de la semana es obligatorio")
    private DiaSemana diaSemana;

    @Column(name = "horario")
    private LocalTime horario;

    @Column(name = "descripcion", length = 500)
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @NotNull(message = "El plan nutricional es obligatorio")
    private PlanNutricional plan;
}
