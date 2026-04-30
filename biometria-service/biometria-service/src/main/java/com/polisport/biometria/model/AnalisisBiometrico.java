package com.polisport.biometria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
