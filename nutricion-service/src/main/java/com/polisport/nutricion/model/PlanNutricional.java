package com.polisport.nutricion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


@Entity
@Getter
@Setter
@ToString(exclude = {"restricciones", "pautas", "suplementos"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planes_nutricionales")
public class PlanNutricional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "atleta_id", nullable = false)
    @NotNull(message = "El Id del Atleta es obligatorio")
    private Long atletaId;

    @Column(name = "deporte", nullable = false, length = 100)
    @NotBlank(message = "El deporte es obligatorio")
    private String deporte;

    @Enumerated(EnumType.STRING)
    @Column(name = "objetivo", nullable = false, length = 30)
    @NotNull(message = "El objetivo nutricional es obligatorio")
    private ObjetivoNutricional objetivo;

    @Column(name = "fecha_inicio")
    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private LocalDate fechaFin;

    @Column(name = "calorias_diarias_gr")
    @NotNull(message = "Las calorias diarias son obligatorias")
    @Min(value = 1200, message = "Las calorias diarias deben ser al menos 1200")
    private Integer caloriasDiariasGr;

    @Column(name = "proteina_gr")
    @NotNull(message = "Las proteinas diarias son obligatorias")
    @Min(value = 50, message = "Las proteinas diarias deben ser al menos 50 gramos")
    private Integer proteinaGr;

    @Column(name = "carbohidratos_gr")
    @NotNull(message = "Los carbohidratos diarios son obligatorios")
    @Min(value = 45, message = "Los carbohidratos diarios deben ser al menos 45 gramos")
    private Integer carbohidratosGr;

    @Column(name = "lipidos_gr")
    @NotNull(message = "Los lipidos diarios son obligatorios")
    @Min(value = 30, message = "Los lipidos diarios deben ser al menos 30 gramos")
    private Integer lipidosGr;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private PlanEstado estado = PlanEstado.BORRADOR;

    @Column(name = "notas", length = 500)
    private String notas;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestriccionAlimentaria> restricciones = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PautaAlimentaria> pautas = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Suplementacion> suplementos = new ArrayList<>();
}
