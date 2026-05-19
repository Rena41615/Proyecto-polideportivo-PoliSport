package com.polisport.iam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @Size(max = 300, message = "La descripción no puede exceder 300 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "permiso")
    @JsonManagedReference("permiso-roles")
    private List<PermisosRol> roles = new ArrayList<>();
}
