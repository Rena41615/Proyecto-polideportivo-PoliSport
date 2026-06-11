package com.polisport.staff.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "miembros_staff")
@Schema(name = "MiembrosStaff", description = "Miembro del staff del complejo deportivo")
public class MiembrosStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del miembro", example = "1")
    private Long id;

    @Column(name = "run")
    @Schema(name = "run", description = "RUN del miembro staff sin dígito verificador", example = "18456789")
    private Integer run;

    @Column(name = "dv", length = 1)
    @Schema(name = "dv", description = "Dígito verificador del RUN", example = "2")
    private String dv;

    @Column(name = "nombre", nullable = false, length = 80)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede exceder 80 caracteres")
    @Schema(name = "nombre", description = "Nombre del miembro staff", example = "Carlos")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 80)
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 80, message = "El apellido no puede exceder 80 caracteres")
    @Schema(name = "apellido", description = "Apellido del miembro staff", example = "Rodríguez")
    private String apellido;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    @NotBlank(message = "El documento es obligatorio")
    @Schema(name = "documento", description = "Número de documento único", example = "18456789-2")
    private String documento;

    @Column(name = "telefono", length = 20)
    @Schema(name = "telefono", description = "Número de teléfono del miembro", example = "+56912345678")
    private String telefono;

    @Column(name = "email", length = 120)
    @Email(message = "El email debe ser valido")
    @Schema(name = "email", description = "Correo electrónico del miembro", example = "carlos@polisport.cl")
    private String email;

    @Column(name = "puesto", length = 100)
    @Schema(name = "puesto", description = "Puesto o cargo del miembro", example = "Entrenador Personal")
    private String puesto;

    @Column(name = "fecha_ingreso")
    @Schema(name = "fechaIngreso", description = "Fecha de ingreso al staff", example = "2023-05-10")
    private LocalDate fechaIngreso;

    @Column(name = "activo", nullable = false)
    @NotNull(message = "El estado es obligatorio")
    @Schema(name = "activo", description = "Estado activo del miembro", example = "true")
    private Boolean activo = true;

    @Column(name = "observaciones", length = 500)
    @Schema(name = "observaciones", description = "Observaciones adicionales sobre el miembro", example = "Especialista en fitness")
    private String observaciones;

    @OneToMany(mappedBy = "staff")
    @Schema(name = "roles", description = "Roles asignados al miembro staff", example = "[]")
    private List<MiembrosRolStaff> roles = new ArrayList<>();

    public MiembrosStaff() {
    }

    public MiembrosStaff(Long id, Integer run, String dv, String nombre, String apellido, String documento, String telefono, String email, String puesto, LocalDate fechaIngreso, Boolean activo, String observaciones, List<MiembrosRolStaff> roles) {
        this.id = id;
        this.run = run;
        this.dv = dv;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.observaciones = observaciones;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<MiembrosRolStaff> getRoles() {
        return roles;
    }

    public void setRoles(List<MiembrosRolStaff> roles) {
        this.roles = roles;
    }
}
