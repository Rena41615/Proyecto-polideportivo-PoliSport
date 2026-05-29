package com.polisport.iam.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PermisosCrearDTO {

    @NotBlank(message = "El nombre del permiso es obligatorio")
    @Size(min = 3, max = 120, message = "El nombre debe tener entre 3 y 120 caracteres")
    private String nombre;

    @Size(max = 300, message = "La descripcion no puede exceder 300 caracteres")
    private String descripcion;
}