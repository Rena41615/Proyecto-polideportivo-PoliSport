package com.polisport.common.dto.contratos;

import com.polisport.contratos.model.TipoContrato;
import com.polisport.contratos.model.EstadoContrato;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ContratoCrearDTO {

    @NotNull(message = "El RUN del empleado es obligatorio")
    @Positive(message = "El RUN debe ser válido")
    private Integer runEmpleado;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 carácter")
    private String dvrunEmpleado;

    @NotBlank(message = "El cargo es obligatorio")
    @Size(min = 3, max = 100, message = "El cargo debe tener entre 3 y 100 caracteres")
    private String cargo;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio debe ser hoy o en el pasado")
    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    @NotNull(message = "El salario mensual es obligatorio")
    @Positive(message = "El salario debe ser mayor a cero")
    private Integer salarioMensual;

    @NotNull(message = "El tipo de contrato es obligatorio")
    private TipoContrato tipoContrato;

    @NotNull(message = "El estado del contrato es obligatorio")
    private EstadoContrato estado;
}
