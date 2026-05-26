package com.polisport.common.dto.iam;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuariosDTO {

    private Long id;
    private String email;
    private String nombre;
    private String contrasena;
}
