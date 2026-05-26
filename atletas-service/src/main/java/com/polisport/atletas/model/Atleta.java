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

    @NotNull(message = "El RUN es obligatorio")
    @Positive(message = "El RUN debe ser valido")
    @Column(unique = true, nullable = false)
    private Integer runAtleta;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 caracter")
    @Column(nullable = false)
    private String dvrunAtleta;

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

    @Email(message = "El email es invalido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotNull(message = "la fecha de nacimiento es obligatoria")
    @Past(message = "Fecha de nacimiento invalida")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El deporte principal es obligatorio")
    private String deportePrincipal;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;

    @Size(max = 1000, message = "El historial deportivo excede los 1000 caracteres")
    private String historialDeportivo;

}