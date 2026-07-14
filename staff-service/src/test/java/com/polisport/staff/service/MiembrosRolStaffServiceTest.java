package com.polisport.staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.polisport.staff.model.MiembrosRolStaff;
import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.repository.MiembrosRolStaffRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Pruebas unitarias de {@link MiembrosRolStaffService}.
 * <p>
 * Verifica el comportamiento CRUD delegado en {@link MiembrosRolStaffRepository},
 * cubriendo los casos de exito y de ausencia de resultados.
 */
@ExtendWith(MockitoExtension.class)
class MiembrosRolStaffServiceTest {

    @Mock
    private MiembrosRolStaffRepository miembrosRolStaffRepository;

    @InjectMocks
    private MiembrosRolStaffService miembrosRolStaffService;

    @Test
    void shouldReturnAllStaffRoles() {
        MiembrosRolStaff rol1 = new MiembrosRolStaff();
        rol1.setId(1L);
        MiembrosRolStaff rol2 = new MiembrosRolStaff();
        rol2.setId(2L);
        when(miembrosRolStaffRepository.findAll()).thenReturn(Arrays.asList(rol1, rol2));

        List<MiembrosRolStaff> resultado = miembrosRolStaffService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(miembrosRolStaffRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnStaffRoleWhenFound() {
        MiembrosStaff staff = new MiembrosStaff();
        staff.setId(1L);
        MiembrosRolStaff rol = new MiembrosRolStaff();
        rol.setId(1L);
        rol.setStaff(staff);
        rol.setAsignadoDesde(LocalDate.of(2024, 1, 1));
        when(miembrosRolStaffRepository.findById(1L)).thenReturn(Optional.of(rol));

        Optional<MiembrosRolStaff> resultado = miembrosRolStaffService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getStaff().getId());
    }

    @Test
    void shouldReturnEmptyWhenStaffRoleNotFound() {
        when(miembrosRolStaffRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<MiembrosRolStaff> resultado = miembrosRolStaffService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveStaffRole() {
        MiembrosRolStaff rol = new MiembrosRolStaff();
        MiembrosRolStaff rolGuardado = new MiembrosRolStaff();
        rolGuardado.setId(1L);
        when(miembrosRolStaffRepository.save(rol)).thenReturn(rolGuardado);

        MiembrosRolStaff resultado = miembrosRolStaffService.guardarMiembrosRolStaff(rol);

        assertEquals(1L, resultado.getId());
        verify(miembrosRolStaffRepository, times(1)).save(rol);
    }
}
