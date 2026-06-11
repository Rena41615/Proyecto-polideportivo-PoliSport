package com.polisport.biometria.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
@Entity
@Table(name = "analisis_biometrico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "AnalisisBiometrico", description = "Análisis biométrico de un atleta")
public class AnalisisBiometrico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del análisis", example = "1")
    private Long id;

    @NotNull(message = "El ID del atleta no puede ser nulo")
    @Positive(message = "El ID del atleta debe ser mayor a 0")
    @Column(name = "atleta_id", nullable = false)
    @Schema(name = "atletaId", description = "Identificador del atleta", example = "5")
    private Long atletaId;

    @NotBlank(message = "La fecha no puede estar vacia")
    @Column(name = "fecha", nullable = false)
    @Schema(name = "fecha", description = "Fecha del análisis biométrico", example = "2026-06-11")
    private String fecha;

    @Positive(message = "El peso debe ser mayor a 0")
    @Column(name = "peso")
    @Schema(name = "peso", description = "Peso del atleta en kilogramos", example = "75.5")
    private double peso;

    @Positive(message = "La altura debe ser mayor a 0")
    @Column(name = "altura")
    @Schema(name = "altura", description = "Altura del atleta en metros", example = "1.82")
    private double altura;

    @Positive(message = "El IMC debe ser un valor positivo")
    @Column(name = "imc")
    @Schema(name = "imc", description = "Índice de Masa Corporal", example = "22.8")
    private double imc;

    @Positive(message = "El porcentaje de grasa debe ser positivo")
    @Column(name = "porcentaje_grasa")
    @Schema(name = "porcentajeGrasa", description = "Porcentaje de grasa corporal", example = "15.3")
    private double porcentajeGrasa;

    @Positive(message = "La masa muscular debe ser un valor positivo")
    @Column(name = "masa_muscular")
    @Schema(name = "masaMuscular", description = "Masa muscular en kilogramos", example = "62.4")
    private double masaMuscular;

    @Positive(message = "El VO2 Max debe ser positivo")
    @Column(name = "vo2_max")
    @Schema(name = "vo2Max", description = "Consumo máximo de oxígeno en ml/kg/min", example = "58.5")
    private double vo2Max;

    @Positive(message = "La frecuencia cardiaca en reposo debe ser positiva")
    @Column(name = "frecuencia_cardiaca_reposo")
    @Schema(name = "frecuenciaCardiacaReposo", description = "Frecuencia cardíaca en reposo en latidos por minuto", example = "60")
    private int frecuenciaCardiacaReposo;

    @Positive(message = "El indicador de rendimiento debe ser positivo")
    @Column(name = "indicador_rendimiento")
    @Schema(name = "indicadorRendimiento", description = "Indicador de rendimiento del atleta", example = "92.5")
    private double indicadorRendimiento;
}