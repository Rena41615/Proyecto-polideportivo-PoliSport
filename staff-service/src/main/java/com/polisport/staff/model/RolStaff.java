package com.polisport.staff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "rol_staff")
@Schema(name = "RolStaff", description = "Rol de personal en el staff")
public class RolStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Identificador único del rol", example = "1")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80, unique = true)
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 80, message = "El nombre no puede exceder 80 caracteres")
    @Schema(name = "nombre", description = "Nombre único del rol staff", example = "Instructor de Yoga")
    private String nombre;

    @Column(name = "descripcion", length = 200)
    @Size(max = 200, message = "La descripcion no puede exceder 200 caracteres")
    @Schema(name = "descripcion", description = "Descripción del rol staff", example = "Encargado de impartir clases de yoga y pilates")
    private String descripcion;

    public RolStaff() {
    }

    public RolStaff(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
}
