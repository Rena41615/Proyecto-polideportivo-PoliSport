package com.polisport.biometria.dto;

import lombok.*;

/**
 * AnalisisBiometricoDTO: DTO para respuestas de análisis biométricos.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AnalisisBiometricoDTO {

    private Long id;
    private Long atletaId;
    private String fecha;
    private Double peso;
    private Double altura;
    private Double imc;
    private Double porcentajeGrasa;
    private Double masaMuscular;
    private Double vo2Max;
    private Integer frecuenciaCardiacaReposo;
    private Double indicadorRendimiento;
}
