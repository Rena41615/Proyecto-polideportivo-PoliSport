package com.polisport.biometria.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
