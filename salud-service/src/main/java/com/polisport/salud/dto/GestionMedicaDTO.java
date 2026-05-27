package com.polisport.salud.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class GestionMedicaDTO {

    private Long id;
    private Long atletaId;
    private String tipoLesion;
    private LocalDate fechaDiagnostico;
    private String descripcion;
    private String tratamiento;
    private String observaciones;
}
