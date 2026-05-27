package com.polisport.atletas.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

/**
 * AtletaCrearDTO: DTO para crear un nuevo atleta.
 * Se valida aquí antes de ser convertido a entidad JPA.
 * NO contiene ID (el servidor lo genera).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtletaCrearDTO {

    @NotNull(message = "El RUN es obligatorio")
    @Positive(message = "El RUN debe ser un número positivo")
    private Integer runAtleta;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 carácter")
    private String dvrunAtleta;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String primerNombre;

    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String segundoNombre;

    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String tercerNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 1, max = 100, message = "El apellido debe tener entre 1 y 100 caracteres")
    private String primerApellido;

    @NotBlank(message = "El segundo apellido es obligatorio")
    @Size(min = 1, max = 100, message = "El apellido debe tener entre 1 y 100 caracteres")
    private String segundoApellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El deporte principal es obligatorio")
    @Size(min = 1, max = 100, message = "El deporte debe tener entre 1 y 100 caracteres")
    private String deportePrincipal;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(min = 1, max = 50, message = "La categoría debe tener entre 1 y 50 caracteres")
    private String categoria;

    @Size(max = 1000, message = "El historial deportivo no puede exceder 1000 caracteres")
    private String historialDeportivo;
}
