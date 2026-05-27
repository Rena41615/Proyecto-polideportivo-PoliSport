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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "miembros_staff")
public class MiembrosStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede exceder 80 caracteres")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 80)
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 80, message = "El apellido no puede exceder 80 caracteres")
    private String apellido;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 120)
    @Email(message = "El email debe ser valido")
    private String email;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "activo", nullable = false)
    @NotNull(message = "El estado es obligatorio")
    private Boolean activo = true;

    @OneToMany(mappedBy = "staff")
    private List<MiembrosRolStaff> roles = new ArrayList<>();
}
