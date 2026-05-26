package com.polisport.common.dto.iam;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RolDTO {

    private Long id;
    private String nombreRol;
    private String descripcion;
}
