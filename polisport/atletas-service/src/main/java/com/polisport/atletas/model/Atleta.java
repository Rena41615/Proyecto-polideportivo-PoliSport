package com.polisport.atletas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @Email(message = "El gmail esta vacio o es inválido")
    private String gmail;

}