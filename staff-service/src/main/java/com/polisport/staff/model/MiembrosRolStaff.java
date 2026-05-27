package com.polisport.staff.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "miembros_rol_staff")
public class MiembrosRolStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    @NotNull(message = "El miembro del staff es obligatorio")
    @JsonIgnoreProperties("roles") // <--- ¡ANADE ESTO AQUI!
    private MiembrosStaff staff;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, columnDefinition = "VARCHAR(50)")
    @NotNull(message = "El rol es obligatorio")
    private RolStaff rolStaff;

    @Column(name = "asignado_desde")
    private LocalDate asignadoDesde = LocalDate.now();
}