package com.polisport.iam.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RolDTO {

    private Long id;
    private String nombreRol;
    private String descripcion;
}
