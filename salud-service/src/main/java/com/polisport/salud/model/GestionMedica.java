package com.polisport.salud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "El ID del atleta no puede ser nulo")
    @Positive(message = "El ID del atleta debe ser mayor a 0")
    private Long atletaId;

    @NotBlank(message = "El tipo de lesión no puede estar vacío")
    private String tipoLesion;

    @NotBlank(message = "La descripción de la lesión no puede estar vacía")
    private String descripcion;

    @NotBlank(message = "La fecha de la lesión es obligatoria")
    private String fechaLesion;

    // Se deja sin @NotBlank para permitir el registro de lesiones activas sin fecha de alta
    private String fechaRetorno;

    @NotBlank(message = "El estado de retorno no puede estar vacío")
    private String estadoRetorno;

    @NotNull(message = "El ID del médico responsable no puede ser nulo")
    @Positive(message = "El ID del médico debe ser mayor a 0")
    private Long medicoId;

    @NotBlank(message = "El tratamiento no puede estar vacío")
    private String tratamiento;
}