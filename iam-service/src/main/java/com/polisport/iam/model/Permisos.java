package com.polisport.iam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permisos")
public class Permisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120, unique = true)
    @NotBlank(message = "El nombre del permiso es obligatorio")
    @Size(max = 120, message = "El nombre no puede exceder 120 caracteres")
    private String nombre;

    @Column(name = "descripcion", length = 300)
    @Size(max = 300, message = "La descripcion no puede exceder 300 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "permiso")
    private List<PermisosRol> roles = new ArrayList<>();

    public Permisos() {
    }

    public Permisos(Long id, String nombre, String descripcion, List<PermisosRol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.roles = roles;
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

    public List<PermisosRol> getRoles() {
        return roles;
    }

    public void setRoles(List<PermisosRol> roles) {
        this.roles = roles;
    }
}
