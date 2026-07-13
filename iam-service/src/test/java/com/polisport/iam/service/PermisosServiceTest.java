package com.polisport.iam.service;

import com.polisport.iam.model.Permisos;
import com.polisport.iam.repository.PermisosRepository;
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
class PermisosServiceTest {

    @Mock
    private PermisosRepository permisosRepository;

    @InjectMocks
    private PermisosService permisosService;

    @Test
    void shouldReturnAllPermisos() {
        Permisos permiso1 = new Permisos(1L, "CREAR_USUARIO", "Permite crear usuarios", null);
        Permisos permiso2 = new Permisos(2L, "ELIMINAR_USUARIO", "Permite eliminar usuarios", null);
        when(permisosRepository.findAll()).thenReturn(Arrays.asList(permiso1, permiso2));

        List<Permisos> resultado = permisosService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(permisosRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnPermisoWhenIdExists() {
        Permisos permiso = new Permisos(1L, "CREAR_USUARIO", "Permite crear usuarios", null);
        when(permisosRepository.findById(1L)).thenReturn(Optional.of(permiso));

        Optional<Permisos> resultado = permisosService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("CREAR_USUARIO", resultado.get().getNombre());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(permisosRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Permisos> resultado = permisosService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSavePermiso() {
        Permisos permiso = new Permisos(null, "CREAR_USUARIO", "Permite crear usuarios", null);
        Permisos permisoGuardado = new Permisos(1L, "CREAR_USUARIO", "Permite crear usuarios", null);
        when(permisosRepository.save(any(Permisos.class))).thenReturn(permisoGuardado);

        Permisos resultado = permisosService.guardarPermisos(permiso);

        assertEquals(1L, resultado.getId());
        verify(permisosRepository, times(1)).save(permiso);
    }
}
