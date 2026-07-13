package com.polisport.staff.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class MiembrosStaffDTO {

    private Long id;
    private Integer run;
    private String dv;
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String puesto;
    private LocalDate fechaIngreso;
    private String observaciones;
}
