package com.polisport.iam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permisos_rol")
public class PermisosRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @NotNull(message = "El rol es obligatorio")
    @JsonBackReference("rol-permisos")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    @NotNull(message = "El permiso es obligatorio")
    @JsonBackReference("permiso-roles")
    private Permisos permiso;
}
