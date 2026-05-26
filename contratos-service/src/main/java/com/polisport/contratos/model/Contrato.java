package com.polisport.contratos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "contratos")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El RUN del empleado es obligatorio")
    @Positive(message = "El RUN debe ser valido")
    @Column(nullable = false)
    private Integer runEmpleado;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El DV debe ser de 1 caracter")
    @Column(nullable = false)
    private String dvrunEmpleado;

    @NotBlank(message = "El cargo o rol es obligatorio")
    private String cargo; // Ej: Entrenador, Recepcionista, Preparador Fisico

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    // Puede ser nula si el tipo de contrato es INDEFINIDO
    private LocalDate fechaTermino;

    @NotNull(message = "El salario mensual es obligatorio")
    @Positive(message = "El salario debe ser mayor a cero")
    private Integer salarioMensual;

    @NotNull(message = "El tipo de contrato es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    private TipoContrato tipoContrato;

    @NotNull(message = "El estado del contrato es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)")
    private EstadoContrato estado;
}