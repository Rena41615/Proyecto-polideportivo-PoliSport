package com.polisport.iam.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PermisosRolDTO {

    private Long id;
    private Long rolId;
    private String rolNombre;
    private Long permisoId;
    private String permisoNombre;
}