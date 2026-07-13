package com.polisport.iam.service;

import com.polisport.iam.model.Rol;
import com.polisport.iam.model.RolesUsuarios;
import com.polisport.iam.model.Usuarios;
import com.polisport.iam.repository.RolesUsuariosRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolesUsuariosServiceTest {

    @Mock
    private RolesUsuariosRepository rolesUsuariosRepository;

    @InjectMocks
    private RolesUsuariosService rolesUsuariosService;

    @Test
    void shouldReturnAllRolesUsuarios() {
        Usuarios usuario = new Usuarios(1L, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        RolesUsuarios ru1 = new RolesUsuarios(1L, usuario, rol, LocalDateTime.now());
        RolesUsuarios ru2 = new RolesUsuarios(2L, usuario, rol, LocalDateTime.now());
        when(rolesUsuariosRepository.findAll()).thenReturn(Arrays.asList(ru1, ru2));

        List<RolesUsuarios> resultado = rolesUsuariosService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(rolesUsuariosRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnRolesUsuariosWhenIdExists() {
        Usuarios usuario = new Usuarios(1L, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        RolesUsuarios ru = new RolesUsuarios(1L, usuario, rol, LocalDateTime.now());
        when(rolesUsuariosRepository.findById(1L)).thenReturn(Optional.of(ru));

        Optional<RolesUsuarios> resultado = rolesUsuariosService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(rolesUsuariosRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<RolesUsuarios> resultado = rolesUsuariosService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveRolesUsuarios() {
        Usuarios usuario = new Usuarios(1L, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        RolesUsuarios ru = new RolesUsuarios(null, usuario, rol, LocalDateTime.now());
        RolesUsuarios ruGuardado = new RolesUsuarios(1L, usuario, rol, LocalDateTime.now());
        when(rolesUsuariosRepository.save(any(RolesUsuarios.class))).thenReturn(ruGuardado);

        RolesUsuarios resultado = rolesUsuariosService.guardarRolesUsuarios(ru);

        assertEquals(1L, resultado.getId());
        verify(rolesUsuariosRepository, times(1)).save(ru);
    }
}
