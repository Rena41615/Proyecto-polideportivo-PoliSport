package com.polisport.inventario.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class InventarioDTO {

    private Long id;
    private String nombreArticulo;
    private String descripcion;
    private Integer cantidad;
    private String ubicacion;
}
