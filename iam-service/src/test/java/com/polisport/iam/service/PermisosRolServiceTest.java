package com.polisport.iam.service;

import com.polisport.iam.model.Permisos;
import com.polisport.iam.model.PermisosRol;
import com.polisport.iam.model.Rol;
import com.polisport.iam.repository.PermisosRolRepository;
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
class PermisosRolServiceTest {

    @Mock
    private PermisosRolRepository permisosRolRepository;

    @InjectMocks
    private PermisosRolService permisosRolService;

    @Test
    void shouldReturnAllPermisosRol() {
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        Permisos permiso = new Permisos(1L, "CREAR_USUARIO", "Permite crear usuarios", null);
        PermisosRol permisosRol1 = new PermisosRol(1L, rol, permiso);
        PermisosRol permisosRol2 = new PermisosRol(2L, rol, permiso);
        when(permisosRolRepository.findAll()).thenReturn(Arrays.asList(permisosRol1, permisosRol2));

        List<PermisosRol> resultado = permisosRolService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(permisosRolRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnPermisosRolWhenIdExists() {
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        Permisos permiso = new Permisos(1L, "CREAR_USUARIO", "Permite crear usuarios", null);
        PermisosRol permisosRol = new PermisosRol(1L, rol, permiso);
        when(permisosRolRepository.findById(1L)).thenReturn(Optional.of(permisosRol));

        Optional<PermisosRol> resultado = permisosRolService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(permisosRolRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<PermisosRol> resultado = permisosRolService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSavePermisosRol() {
        Rol rol = new Rol(1L, "Administrador", "Acceso total", null, null);
        Permisos permiso = new Permisos(1L, "CREAR_USUARIO", "Permite crear usuarios", null);
        PermisosRol permisosRol = new PermisosRol(null, rol, permiso);
        PermisosRol permisosRolGuardado = new PermisosRol(1L, rol, permiso);
        when(permisosRolRepository.save(any(PermisosRol.class))).thenReturn(permisosRolGuardado);

        PermisosRol resultado = permisosRolService.guardarPermisosRol(permisosRol);

        assertEquals(1L, resultado.getId());
        verify(permisosRolRepository, times(1)).save(permisosRol);
    }
}
