package com.polisport.salud.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "Gestion_medica")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GestionMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del atleta no puede ser nulo")
    @Positive(message = "El ID del atleta debe ser mayor a 0")
    @Column(name = "atleta_id", nullable = false)
    private Long atletaId;

    @NotBlank(message = "El tipo de lesion no puede estar vacio")
    @Column(name = "tipo_lesion", nullable = false)
    private String tipoLesion;

    @NotBlank(message = "La descripcion de la lesion no puede estar vacia")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotBlank(message = "La fecha de la lesion es obligatoria")
    @Column(name = "fecha_lesion", nullable = false)
    private String fechaLesion;

    // Se deja sin @NotBlank para permitir el registro de lesiones activas sin fecha de alta
    @Column(name = "fecha_retorno")
    private String fechaRetorno;

    @NotBlank(message = "El estado de retorno no puede estar vacio")
    @Column(name = "estado_retorno", nullable = false)
    private String estadoRetorno;

    @NotNull(message = "El ID del medico responsable no puede ser nulo")
    @Positive(message = "El ID del medico debe ser mayor a 0")
    @Column(name = "medico_id", nullable = false)
    private Long medicoId;

    @NotBlank(message = "El tratamiento no puede estar vacio")
    @Column(name = "tratamiento", nullable = false)
    private String tratamiento;
}