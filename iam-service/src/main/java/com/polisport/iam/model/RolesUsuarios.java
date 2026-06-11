package com.polisport.iam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "roles_usuarios")
@Schema(name = "RolesUsuarios", description = "Relación entre usuario y rol")
public class RolesUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único de la relación", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "El usuario es obligatorio")
    @JsonIgnoreProperties("roles")
    @Schema(name = "usuario", description = "Usuario asignado al rol", example = "{\"id\": 1}")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @NotNull(message = "El rol es obligatorio")
    @JsonIgnoreProperties("usuarios")
    @Schema(name = "rol", description = "Rol asignado al usuario", example = "{\"id\": 1}")
    private Rol rol;

    @Column(name = "fecha_asignacion", nullable = false)
    @Schema(name = "fechaAsignacion", description = "Fecha y hora de asignación del rol", example = "2024-01-20T15:45:00")
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    public RolesUsuarios() {
    }

    public RolesUsuarios(Long id, Usuarios usuario, Rol rol, LocalDateTime fechaAsignacion) {
        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
