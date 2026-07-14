package com.polisport.staff.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class MiembrosStaffCrearDTO {

    @NotNull(message = "El RUN es obligatorio")
    @Positive(message = "El RUN debe ser válido")
    private Integer run;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 carácter")
    private String dv;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 80, message = "El apellido debe tener entre 3 y 80 caracteres")
    private String apellido;

    @NotBlank(message = "El documento es obligatorio")
    @Size(max = 20, message = "El documento no puede exceder 20 caracteres")
    private String documento;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "El puesto es obligatorio")
    @Size(min = 3, max = 100, message = "El puesto debe tener entre 3 y 100 caracteres")
    private String puesto;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha debe ser hoy o en el pasado")
    private LocalDate fechaIngreso;

    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;
}
