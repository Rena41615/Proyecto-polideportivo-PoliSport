package com.polisport.salud.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "Gestion_medica")
@Schema(name = "GestionMedica", description = "Registro médico y de salud de un atleta")
public class GestionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del registro médico", example = "1")
    private Long id;

    @NotNull(message = "El ID del atleta no puede ser nulo")
    @Positive(message = "El ID del atleta debe ser mayor a 0")
    @Column(name = "atleta_id", nullable = false)
    @Schema(name = "atletaId", description = "Identificador del atleta", example = "25")
    private Long atletaId;

    @NotBlank(message = "El tipo de lesion no puede estar vacio")
    @Column(name = "tipo_lesion", nullable = false)
    @Schema(name = "tipoLesion", description = "Tipo de lesión deportiva", example = "Esguince de tobillo")
    private String tipoLesion;

    @NotNull(message = "La fecha de diagnostico es obligatoria")
    @Column(name = "fecha_diagnostico", nullable = false)
    @Schema(name = "fechaDiagnostico", description = "Fecha del diagnóstico médico", example = "2025-06-10")
    private LocalDate fechaDiagnostico;

    @NotBlank(message = "La descripcion de la lesion no puede estar vacia")
    @Column(name = "descripcion", nullable = false)
    @Schema(name = "descripcion", description = "Descripción detallada de la lesión", example = "Esguince grado II en tobillo derecho con inflamación moderada")
    private String descripcion;

    @Column(name = "fecha_retorno")
    @Schema(name = "fechaRetorno", description = "Fecha estimada de retorno a actividades deportivas", example = "2025-07-10")
    private String fechaRetorno;

    @NotBlank(message = "El estado de retorno no puede estar vacio")
    @Column(name = "estado_retorno", nullable = false)
    @Schema(name = "estadoRetorno", description = "Estado actual del atleta respecto al retorno", example = "EN_RECUPERACION")
    private String estadoRetorno;

    @NotNull(message = "El ID del medico responsable no puede ser nulo")
    @Positive(message = "El ID del medico debe ser mayor a 0")
    @Column(name = "medico_id", nullable = false)
    @Schema(name = "medicoId", description = "Identificador del médico responsable", example = "10")
    private Long medicoId;

    @NotBlank(message = "El tratamiento no puede estar vacio")
    @Column(name = "tratamiento", nullable = false)
    @Schema(name = "tratamiento", description = "Tratamiento recomendado para la lesión", example = "Reposo, hielo, compresión, elevación y fisioterapia")
    private String tratamiento;

    @Column(name = "observaciones")
    @Schema(name = "observaciones", description = "Observaciones adicionales sobre el seguimiento del atleta", example = "Atleta responde bien al tratamiento, requiere seguimiento semanal")
    private String observaciones;

    public GestionMedica() {
    }

    public GestionMedica(Long id, Long atletaId, String tipoLesion, LocalDate fechaDiagnostico, String descripcion, String fechaRetorno, String estadoRetorno, Long medicoId, String tratamiento, String observaciones) {
        this.id = id;
        this.atletaId = atletaId;
        this.tipoLesion = tipoLesion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.descripcion = descripcion;
        this.fechaRetorno = fechaRetorno;
        this.estadoRetorno = estadoRetorno;
        this.medicoId = medicoId;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Long atletaId) {
        this.atletaId = atletaId;
    }

    public String getTipoLesion() {
        return tipoLesion;
    }

    public void setTipoLesion(String tipoLesion) {
        this.tipoLesion = tipoLesion;
    }

    public LocalDate getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(LocalDate fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(String fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public String getEstadoRetorno() {
        return estadoRetorno;
    }

    public void setEstadoRetorno(String estadoRetorno) {
        this.estadoRetorno = estadoRetorno;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
