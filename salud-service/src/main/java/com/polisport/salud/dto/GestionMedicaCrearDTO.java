package com.polisport.salud.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class GestionMedicaCrearDTO {

    @NotNull(message = "El ID del atleta es obligatorio")
    @Positive(message = "El ID del atleta debe ser positivo")
    private Long atletaId;

    @NotBlank(message = "El tipo de lesión es obligatorio")
    @Size(min = 3, max = 100, message = "El tipo debe tener entre 3 y 100 caracteres")
    private String tipoLesion;

    @NotNull(message = "La fecha de diagnóstico es obligatoria")
    private LocalDate fechaDiagnostico;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;

    @NotBlank(message = "El tratamiento es obligatorio")
    @Size(min = 3, max = 500, message = "El tratamiento debe tener entre 3 y 500 caracteres")
    private String tratamiento;

    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;
}
