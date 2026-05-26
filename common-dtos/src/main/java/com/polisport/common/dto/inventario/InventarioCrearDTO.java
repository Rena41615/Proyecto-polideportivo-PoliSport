package com.polisport.common.dto.inventario;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class InventarioCrearDTO {

    @NotBlank(message = "El nombre del artículo es obligatorio")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    private String nombreArticulo;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @NotNull(message = "La cantidad es obligatoria")
    @PositiveOrZero(message = "La cantidad debe ser cero o positiva")
    private Integer cantidad;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;
}
