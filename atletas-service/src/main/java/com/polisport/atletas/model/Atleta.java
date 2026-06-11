package com.polisport.atletas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "atletas")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Schema(name = "Atleta", description = "Registro de un atleta del complejo deportivo")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del atleta", example = "1")
    private Long id;

    @NotNull(message = "El RUN es obligatorio")
    @Positive(message = "El RUN debe ser valido")
    @Column(unique = true, nullable = false)
    @Schema(name = "runAtleta", description = "RUN (Rol Único Nacional) del atleta", example = "20456789")
    private Integer runAtleta;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 caracter")
    @Column(nullable = false)
    @Schema(name = "dvrunAtleta", description = "Dígito verificador del RUN", example = "K")
    private String dvrunAtleta;

    @NotBlank(message = "El campo es obligatorio")
    @Column(nullable = false)
    @Schema(name = "primerNombre", description = "Primer nombre del atleta", example = "Juan")
    private String primerNombre;

    @Schema(name = "segundoNombre", description = "Segundo nombre del atleta", example = "Carlos")
    private String segundoNombre;

    @Schema(name = "tercerNombre", description = "Tercer nombre del atleta", example = "Andrés")
    private String tercerNombre;

    @NotBlank(message = "El campo es obligatorio")
    @Column(nullable = false)
    @Schema(name = "primerApellido", description = "Primer apellido del atleta", example = "Pérez")
    private String primerApellido;

    @NotBlank(message = "El campo es obligatorio")
    @Column(nullable = false)
    @Schema(name = "segundoApellido", description = "Segundo apellido del atleta", example = "García")
    private String segundoApellido;

    @Email(message = "El email es invalido")
    @NotBlank(message = "El email es obligatorio")
    @Schema(name = "email", description = "Correo electrónico del atleta", example = "juan.perez@polisport.cl")
    private String email;

    @NotNull(message = "la fecha de nacimiento es obligatoria")
    @Past(message = "Fecha de nacimiento invalida")
    @Schema(name = "fechaNacimiento", description = "Fecha de nacimiento del atleta", example = "1998-05-15")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El deporte principal es obligatorio")
    @Schema(name = "deportePrincipal", description = "Deporte principal del atleta", example = "Atletismo")
    private String deportePrincipal;

    @NotBlank(message = "La categoria es obligatoria")
    @Schema(name = "categoria", description = "Categoría del atleta", example = "Junior")
    private String categoria;

    @Size(max = 1000, message = "El historial deportivo excede los 1000 caracteres")
    @Schema(name = "historialDeportivo", description = "Historial deportivo del atleta", example = "Campeón nacional 2023, Subcampeón Sudamericano 2022")
    private String historialDeportivo;

}