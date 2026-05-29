package com.polisport.iam.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PermisosRolCrearDTO {

    @NotNull(message = "El ID del rol es obligatorio")
    private Long rolId;

    @NotNull(message = "El ID del permiso es obligatorio")
    private Long permisoId;
}
