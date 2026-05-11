package com.polisport.iam.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Size(max = 200, message = "La descripción no puede exceder 200 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private List<RolesUsuarios> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "rol")
    private List<PermisosRol> permisos = new ArrayList<>();
}
