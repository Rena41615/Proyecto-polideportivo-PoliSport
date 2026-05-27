package com.polisport.atletas.dto;

import lombok.*;
import java.time.LocalDate;

/**
 * AtletaDTO: DTO para respuestas de atletas.
 * Contiene todos los datos del atleta incluyendo ID.
 * Se usa para GET y respuestas POST.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtletaDTO {

    private Long id;

    private Integer runAtleta;

    private String dvrunAtleta;

    private String primerNombre;

    private String segundoNombre;

    private String tercerNombre;

    private String primerApellido;

    private String segundoApellido;

    private String email;

    private LocalDate fechaNacimiento;

    private String deportePrincipal;

    private String categoria;

    private String historialDeportivo;
}
