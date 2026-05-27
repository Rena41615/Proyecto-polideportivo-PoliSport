package com.polisport.staff.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "miembros_permisos_staff")
public class MiembrosPermisosStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    @NotNull(message = "El miembro del staff es obligatorio")
    private MiembrosStaff staff;

    @Column(name = "permiso", nullable = false, length = 120)
    @NotBlank(message = "El permiso es obligatorio")
    @Size(max = 120, message = "El permiso no puede exceder 120 caracteres")
    private String permiso;

    @Column(name = "descripcion", length = 300)
    @Size(max = 300, message = "La descripcion no puede exceder 300 caracteres")
    private String descripcion;

    @Column(name = "otorgado_desde")
    private LocalDate otorgadoDesde = LocalDate.now();

    public MiembrosPermisosStaff() {
    }

    public MiembrosPermisosStaff(Long id, MiembrosStaff staff, String permiso, String descripcion, LocalDate otorgadoDesde) {
        this.id = id;
        this.staff = staff;
        this.permiso = permiso;
        this.descripcion = descripcion;
        this.otorgadoDesde = otorgadoDesde;
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

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getOtorgadoDesde() {
        return otorgadoDesde;
    }

    public void setOtorgadoDesde(LocalDate otorgadoDesde) {
        this.otorgadoDesde = otorgadoDesde;
    }
}
