package com.polisport.biometria.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
@Entity
@Table(name = "analisis_biometrico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnalisisBiometrico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del atleta no puede ser nulo")
    @Positive(message = "El ID del atleta debe ser mayor a 0")
    @Column(name = "atleta_id", nullable = false)
    private Long atletaId;

    @NotBlank(message = "La fecha no puede estar vacia")
    @Column(name = "fecha", nullable = false)
    private String fecha;

    @Positive(message = "El peso debe ser mayor a 0")
    @Column(name = "peso")
    private double peso;

    @Positive(message = "La altura debe ser mayor a 0")
    @Column(name = "altura")
    private double altura;

    @Positive(message = "El IMC debe ser un valor positivo")
    @Column(name = "imc")
    private double imc;

    @Positive(message = "El porcentaje de grasa debe ser positivo")
    @Column(name = "porcentaje_grasa")
    private double porcentajeGrasa;

    @Positive(message = "La masa muscular debe ser un valor positivo")
    @Column(name = "masa_muscular")
    private double masaMuscular;

    @Positive(message = "El VO2 Max debe ser positivo")
    @Column(name = "vo2_max")
    private double vo2Max;

    @Positive(message = "La frecuencia cardiaca en reposo debe ser positiva")
    @Column(name = "frecuencia_cardiaca_reposo")
    private int frecuenciaCardiacaReposo;

    @Positive(message = "El indicador de rendimiento debe ser positivo")
    @Column(name = "indicador_rendimiento")
    private double indicadorRendimiento;
}