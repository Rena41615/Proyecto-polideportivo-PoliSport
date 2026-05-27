package com.polisport.contratos.dto;

import com.polisport.contratos.model.TipoContrato;
import com.polisport.contratos.model.EstadoContrato;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ContratoDTO {

    private Long id;
    private Integer runEmpleado;
    private String dvrunEmpleado;
    private String cargo;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private Integer salarioMensual;
    private TipoContrato tipoContrato;
    private EstadoContrato estado;
}
