package com.polisport.iam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "permisos_rol")
@Schema(name = "PermisosRol", description = "Relación entre permiso y rol")
public class PermisosRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único de la relación", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @NotNull(message = "El rol es obligatorio")
    @JsonIgnoreProperties("permisos")
    @Schema(name = "rol", description = "Rol al cual se asigna el permiso", example = "{\"id\": 1}")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    @NotNull(message = "El permiso es obligatorio")
    @JsonIgnoreProperties("roles")
    @Schema(name = "permiso", description = "Permiso asignado al rol", example = "{\"id\": 1}")
    private Permisos permiso;

    public PermisosRol() {
    }

    public PermisosRol(Long id, Rol rol, Permisos permiso) {
        this.id = id;
        this.rol = rol;
        this.permiso = permiso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }
}
