package com.polisport.staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.polisport.staff.model.MiembrosPermisosStaff;
import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.repository.MiembrosPermisosStaffRepository;
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
 * Pruebas unitarias de {@link MiembrosPermisosStaffService}.
 * <p>
 * Verifica el comportamiento CRUD delegado en {@link MiembrosPermisosStaffRepository},
 * cubriendo los casos de exito y de ausencia de resultados.
 */
@ExtendWith(MockitoExtension.class)
class MiembrosPermisosStaffServiceTest {

    @Mock
    private MiembrosPermisosStaffRepository miembrosPermisosStaffRepository;

    @InjectMocks
    private MiembrosPermisosStaffService miembrosPermisosStaffService;

    @Test
    void shouldReturnAllStaffPermissions() {
        MiembrosPermisosStaff permiso1 = new MiembrosPermisosStaff();
        permiso1.setId(1L);
        MiembrosPermisosStaff permiso2 = new MiembrosPermisosStaff();
        permiso2.setId(2L);
        when(miembrosPermisosStaffRepository.findAll()).thenReturn(Arrays.asList(permiso1, permiso2));

        List<MiembrosPermisosStaff> resultado = miembrosPermisosStaffService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(miembrosPermisosStaffRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnStaffPermissionWhenFound() {
        MiembrosStaff staff = new MiembrosStaff();
        staff.setId(1L);
        MiembrosPermisosStaff permiso = new MiembrosPermisosStaff();
        permiso.setId(1L);
        permiso.setStaff(staff);
        permiso.setPermiso("GESTIONAR_RESERVAS");
        permiso.setOtorgadoDesde(LocalDate.of(2024, 1, 1));
        when(miembrosPermisosStaffRepository.findById(1L)).thenReturn(Optional.of(permiso));

        Optional<MiembrosPermisosStaff> resultado = miembrosPermisosStaffService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("GESTIONAR_RESERVAS", resultado.get().getPermiso());
    }

    @Test
    void shouldReturnEmptyWhenStaffPermissionNotFound() {
        when(miembrosPermisosStaffRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<MiembrosPermisosStaff> resultado = miembrosPermisosStaffService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveStaffPermission() {
        MiembrosPermisosStaff permiso = new MiembrosPermisosStaff();
        permiso.setPermiso("GESTIONAR_RESERVAS");
        MiembrosPermisosStaff permisoGuardado = new MiembrosPermisosStaff();
        permisoGuardado.setId(1L);
        permisoGuardado.setPermiso("GESTIONAR_RESERVAS");
        when(miembrosPermisosStaffRepository.save(permiso)).thenReturn(permisoGuardado);

        MiembrosPermisosStaff resultado = miembrosPermisosStaffService.guardarMiembrosPermisosStaff(permiso);

        assertEquals(1L, resultado.getId());
        verify(miembrosPermisosStaffRepository, times(1)).save(permiso);
    }
}
