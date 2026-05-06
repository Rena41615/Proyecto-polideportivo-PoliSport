package com.polisport.biometria.model;

import jakarta.persistence.*;
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
    private Long atletaId;
    private String fecha;
    private double peso;
    private double altura;
    private double imc;
    private double porcentajeGrasa;
    private double masaMuscular;
    private double vo2Max;
    private int frecuenciaCardiacaReposo;
    private double indicadorRendimiento;
}