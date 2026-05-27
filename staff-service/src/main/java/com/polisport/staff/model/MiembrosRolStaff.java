package com.polisport.staff.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "miembros_rol_staff")
public class MiembrosRolStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    @NotNull(message = "El miembro del staff es obligatorio")
    @JsonIgnoreProperties("roles")
    private MiembrosStaff staff;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @NotNull(message = "El rol es obligatorio")
    private RolStaff rolStaff;

    @Column(name = "asignado_desde")
    private LocalDate asignadoDesde = LocalDate.now();

    public MiembrosRolStaff() {
    }

    public MiembrosRolStaff(Long id, MiembrosStaff staff, RolStaff rolStaff, LocalDate asignadoDesde) {
        this.id = id;
        this.staff = staff;
        this.rolStaff = rolStaff;
        this.asignadoDesde = asignadoDesde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MiembrosStaff getStaff() {
        return staff;
    }

    public void setStaff(MiembrosStaff staff) {
        this.staff = staff;
    }

    public RolStaff getRolStaff() {
        return rolStaff;
    }

    public void setRolStaff(RolStaff rolStaff) {
        this.rolStaff = rolStaff;
    }

    public LocalDate getAsignadoDesde() {
        return asignadoDesde;
    }

    public void setAsignadoDesde(LocalDate asignadoDesde) {
        this.asignadoDesde = asignadoDesde;
    }
}
