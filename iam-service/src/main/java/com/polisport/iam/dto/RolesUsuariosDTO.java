package com.polisport.iam.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RolesUsuariosDTO {

    private Long id;
    private Long usuarioId;
    private String usuarioEmail;
    private String usuarioNombre;
    private Long rolId;
    private String rolNombre;
    private LocalDateTime fechaAsignacion;
}
