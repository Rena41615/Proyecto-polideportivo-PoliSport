package com.polisport.biometria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class model {
    private Long id;
    private Long atletaId;
    private String fecha;
    //composicion corporal
    private double peso;
    private double altura;
    private double imc;
    private double porcentajeGrasa;
    private double masaMuscular;

    //indicador de rendimiento
    private double vo2Max;
    private int frecuenciaCardiacaReposo;
    private double indicadorRendimiento;

}
