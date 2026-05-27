package com.polisport.iam.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80, unique = true)
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 80, message = "El nombre no puede exceder 80 caracteres")
    private String nombre;

    @Column(name = "descripcion", length = 200)
    @Size(max = 200, message = "La descripcion no puede exceder 200 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private List<RolesUsuarios> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "rol")
    private List<PermisosRol> permisos = new ArrayList<>();

    public Rol() {
    }

    public Rol(Long id, String nombre, String descripcion, List<RolesUsuarios> usuarios, List<PermisosRol> permisos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarios = usuarios;
        this.permisos = permisos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RolesUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<RolesUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<PermisosRol> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisosRol> permisos) {
        this.permisos = permisos;
    }
}
