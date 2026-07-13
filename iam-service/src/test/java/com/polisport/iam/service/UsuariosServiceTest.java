package com.polisport.iam.service;

import com.polisport.iam.model.Usuarios;
import com.polisport.iam.repository.UsuariosRepository;
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
class UsuariosServiceTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private UsuariosService usuariosService;

    @Test
    void shouldReturnAllUsuarios() {
        Usuarios usuario1 = new Usuarios(1L, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        Usuarios usuario2 = new Usuarios(2L, "ana@polisport.cl", "hash2", "Ana", "Diaz", true, LocalDateTime.now(), null);
        when(usuariosRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuarios> resultado = usuariosService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(usuariosRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnUsuarioWhenIdExists() {
        Usuarios usuario = new Usuarios(1L, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuarios> resultado = usuariosService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("juan@polisport.cl", resultado.get().getEmail());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(usuariosRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Usuarios> resultado = usuariosService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveUsuario() {
        Usuarios usuario = new Usuarios(null, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        Usuarios usuarioGuardado = new Usuarios(1L, "juan@polisport.cl", "hash1", "Juan", "Perez", true, LocalDateTime.now(), null);
        when(usuariosRepository.save(any(Usuarios.class))).thenReturn(usuarioGuardado);

        Usuarios resultado = usuariosService.guardarUsuarios(usuario);

        assertEquals(1L, resultado.getId());
        verify(usuariosRepository, times(1)).save(usuario);
    }
}
