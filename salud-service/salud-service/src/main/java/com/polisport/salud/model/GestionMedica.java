package com.polisport.salud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Gestion_medica")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GestionMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long atletaId;
    private String tipoLesion;
    private String descripcion;
    private String fechaLesion;
    private String fechaRetorno;
    private String estadoRetorno;
    private Long medicoId;
    private String tratamiento;
}
