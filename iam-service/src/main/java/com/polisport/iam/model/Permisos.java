package com.polisport.iam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
