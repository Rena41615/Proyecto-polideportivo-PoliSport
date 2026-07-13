package com.polisport.iam.service;

import com.polisport.iam.model.Rol;
import com.polisport.iam.repository.RolRepository;
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
class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void shouldReturnAllRoles() {
        Rol rol1 = new Rol(1L, "Administrador", "Acceso total", null, null);
        Rol rol2 = new Rol(2L, "Entrenador", "Gestiona atletas", null, null);
        when(rolRepository.findAll()).thenReturn(Arrays.asList(rol1, rol2));

        List<Rol> resultado = rolService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnRolWhenIdExists() {
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rol));

        Optional<Rol> resultado = rolService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Administrador", resultado.get().getNombre());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(rolRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Rol> resultado = rolService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveRol() {
        Rol rol = new Rol(null, "Administrador", "Acceso total", null, null);
        Rol rolGuardado = new Rol(1L, "Administrador", "Acceso total", null, null);
        when(rolRepository.save(any(Rol.class))).thenReturn(rolGuardado);

        Rol resultado = rolService.guardarRol(rol);

        assertEquals(1L, resultado.getId());
        verify(rolRepository, times(1)).save(rol);
    }

    @Test
    void shouldDeleteRolById() {
        rolService.eliminarRol(1L);

        verify(rolRepository, times(1)).deleteById(1L);
    }
}
