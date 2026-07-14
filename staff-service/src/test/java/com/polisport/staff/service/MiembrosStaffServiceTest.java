package com.polisport.staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.repository.MiembrosStaffRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Pruebas unitarias de {@link MiembrosStaffService}.
 * <p>
 * Verifica el comportamiento CRUD delegado en {@link MiembrosStaffRepository},
 * cubriendo los casos de exito y de ausencia de resultados.
 */
@ExtendWith(MockitoExtension.class)
class MiembrosStaffServiceTest {

    @Mock
    private MiembrosStaffRepository miembrosStaffRepository;

    @InjectMocks
    private MiembrosStaffService miembrosStaffService;

    @Test
    void shouldReturnAllStaffMembers() {
        MiembrosStaff staff1 = new MiembrosStaff();
        staff1.setId(1L);
        staff1.setDocumento("18456789-2");
        MiembrosStaff staff2 = new MiembrosStaff();
        staff2.setId(2L);
        staff2.setDocumento("19456789-3");
        when(miembrosStaffRepository.findAll()).thenReturn(Arrays.asList(staff1, staff2));

        List<MiembrosStaff> resultado = miembrosStaffService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(miembrosStaffRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnStaffMemberWhenFound() {
        MiembrosStaff staff = new MiembrosStaff();
        staff.setId(1L);
        staff.setDocumento("18456789-2");
        when(miembrosStaffRepository.findById(1L)).thenReturn(Optional.of(staff));

        Optional<MiembrosStaff> resultado = miembrosStaffService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("18456789-2", resultado.get().getDocumento());
    }

    @Test
    void shouldReturnEmptyWhenStaffMemberNotFound() {
        when(miembrosStaffRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<MiembrosStaff> resultado = miembrosStaffService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveStaffMember() {
        MiembrosStaff staff = new MiembrosStaff();
        staff.setDocumento("18456789-2");
        MiembrosStaff staffGuardado = new MiembrosStaff();
        staffGuardado.setId(1L);
        staffGuardado.setDocumento("18456789-2");
        when(miembrosStaffRepository.save(staff)).thenReturn(staffGuardado);

        MiembrosStaff resultado = miembrosStaffService.guardarMiembrosStaff(staff);

        assertEquals(1L, resultado.getId());
        verify(miembrosStaffRepository, times(1)).save(staff);
    }
}
