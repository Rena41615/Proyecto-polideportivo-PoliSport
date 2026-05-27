package com.polisport.nutricion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suplementaciones")
public class Suplementacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    @NotNull(message = "El tipo de suplemento es obligatorio")
    private TipoSuplemento tipo;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre del suplemento es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Column(name = "dosis")
    @Min(value = 1, message = "La dosis debe ser mayor a 0")
    private Integer dosis;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad", length = 20)
    private UnidadMedida unidad;

    @Column(name = "horario")
    private LocalTime horario;

    @Column(name = "observaciones", length = 300)
    @Size(max = 300, message = "Las observaciones no pueden exceder 300 caracteres")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @NotNull(message = "El plan nutricional es obligatorio")
    @JsonIgnoreProperties("suplementos")
    private PlanNutricional plan;
}
