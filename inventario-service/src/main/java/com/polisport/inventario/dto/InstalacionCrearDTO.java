package com.polisport.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class InstalacionCrearDTO {

    @NotBlank(message = "El nombre de la instalación es obligatorio")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    private String nombreInstalacion;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    @NotBlank(message = "El tipo de instalación es obligatorio")
    private String tipo;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un número positivo")
    private Integer capacidad;

    @NotNull(message = "Debe especificar si la instalación está disponible")
    private Boolean disponible;

    @Size(max = 500, message = "El estado no puede exceder 500 caracteres")
    private String estado;
}
