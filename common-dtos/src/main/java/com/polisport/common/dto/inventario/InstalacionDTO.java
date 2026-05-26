package com.polisport.common.dto.inventario;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class InstalacionDTO {

    private Long id;
    private String nombreInstalacion;
    private String descripcion;
    private String ubicacion;
    private String estado;
}
