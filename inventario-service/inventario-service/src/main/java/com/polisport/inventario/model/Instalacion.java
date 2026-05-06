package com.polisport.inventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instalacion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private int capacidad;
    private boolean disponible;
    private String estado;
}