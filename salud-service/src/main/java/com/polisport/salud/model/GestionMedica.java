package com.polisport.salud.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "Gestion_medica")
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

    @NotNull(message = "La fecha de diagnostico es obligatoria")
    @Column(name = "fecha_diagnostico", nullable = false)
    private LocalDate fechaDiagnostico;

    @NotBlank(message = "La descripcion de la lesion no puede estar vacia")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

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

    @Column(name = "observaciones")
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
