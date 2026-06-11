package com.polisport.contratos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "contratos")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Schema(name = "Contrato", description = "Contrato laboral de un empleado")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del contrato", example = "1")
    private Long id;

    @NotNull(message = "El RUN del empleado es obligatorio")
    @Positive(message = "El RUN debe ser valido")
    @Column(nullable = false)
    @Schema(name = "runEmpleado", description = "RUN del empleado sin dígito verificador", example = "18765432")
    private Integer runEmpleado;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 caracter")
    @Column(nullable = false)
    @Schema(name = "dvrunEmpleado", description = "Dígito verificador del RUN del empleado", example = "5")
    private String dvrunEmpleado;

    @NotBlank(message = "El cargo o rol es obligatorio")
    @Schema(name = "cargo", description = "Cargo o rol del empleado", example = "Entrenador")
    private String cargo; // Ej: Entrenador, Recepcionista, Preparador Fisico

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Schema(name = "fechaInicio", description = "Fecha de inicio del contrato", example = "2025-01-15")
    private LocalDate fechaInicio;

    // Puede ser nula si el tipo de contrato es INDEFINIDO
    @Schema(name = "fechaTermino", description = "Fecha de término del contrato, nula si es indefinido", example = "2026-01-15")
    private LocalDate fechaTermino;

    @NotNull(message = "El salario mensual es obligatorio")
    @Positive(message = "El salario debe ser mayor a cero")
    @Schema(name = "salarioMensual", description = "Salario mensual en pesos chilenos", example = "2500000")
    private Integer salarioMensual;

    @NotNull(message = "El tipo de contrato es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "tipoContrato", description = "Tipo de contrato laboral", example = "PLAZO_FIJO")
    private TipoContrato tipoContrato;

    @NotNull(message = "El estado del contrato es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    @Schema(name = "estado", description = "Estado actual del contrato", example = "VIGENTE")
    private EstadoContrato estado;
}