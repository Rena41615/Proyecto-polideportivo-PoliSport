package com.polisport.atletas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "atletas")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor

public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "RUT es un campo obligatorio")
    @Column(unique = true, nullable = false, length = 12)
    private String rut;

    @NotBlank(message = "El campo es obligatorio")
    @Column(nullable = false)
    private String primerNombre;

    private String segundoNombre;

    private String tercerNombre;

    @NotBlank(message = "El campo es obligatorio")
    @Column(nullable = false)
    private String primerApellido;

    @NotBlank(message = "El campo es obligatorio")
    @Column(nullable = false)
    private String segundoApellido;

    @Email(message = "El email es inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotNull(message = "la fecha de nacimiento es obligatoria")
    @Past(message = "Fecha de nacimiento invalida")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El deporte principal es obligatorio")
    private String deportePrincipal;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @Size(max = 1000, message = "El historial deportivo excede los 1000 caracteres")
    private String historialDeportivo;

}