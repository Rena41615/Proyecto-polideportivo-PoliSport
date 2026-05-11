package com.polisport.nutricion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restricciones_alimentarias")
public class RestriccionAlimentaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    @NotNull(message = "El tipo de restricción es obligatorio")
    private TipoRestriccion tipo;

    @Column(name = "descripcion", length = 500)
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    @NotNull(message = "El plan nutricional es obligatorio")
    private PlanNutricional plan;
}
