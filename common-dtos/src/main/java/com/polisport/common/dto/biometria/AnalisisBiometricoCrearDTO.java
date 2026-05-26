package com.polisport.common.dto.biometria;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * AnalisisBiometricoCrearDTO: DTO para crear análisis biométricos.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AnalisisBiometricoCrearDTO {

    @NotNull(message = "El ID del atleta es obligatorio")
    @Positive(message = "El ID del atleta debe ser positivo")
    private Long atletaId;

    @NotBlank(message = "La fecha es obligatoria")
    private String fecha;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double peso;

    @NotNull(message = "La altura es obligatoria")
    @Positive(message = "La altura debe ser mayor a 0")
    private Double altura;

    @NotNull(message = "El IMC es obligatorio")
    @Positive(message = "El IMC debe ser mayor a 0")
    private Double imc;

    @Positive(message = "El porcentaje de grasa debe ser positivo")
    private Double porcentajeGrasa;

    @Positive(message = "La masa muscular debe ser positiva")
    private Double masaMuscular;

    @Positive(message = "El VO2 Max debe ser positivo")
    private Double vo2Max;

    @Positive(message = "La frecuencia cardíaca debe ser positiva")
    private Integer frecuenciaCardiacaReposo;

    @Positive(message = "El indicador de rendimiento debe ser positivo")
    private Double indicadorRendimiento;
}
