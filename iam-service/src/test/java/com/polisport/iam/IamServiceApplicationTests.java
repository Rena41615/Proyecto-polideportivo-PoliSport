package com.polisport.iam;

import com.polisport.iam.model.Usuarios;
import com.polisport.iam.repository.UsuariosRepository;
import com.polisport.iam.service.UsuariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class IamServiceApplicationTests {

	@Autowired
	private UsuariosService usuariosService;

	@MockBean
	private UsuariosRepository usuariosRepository;

	private Usuarios usuario;

	@BeforeEach
	void setUp() {
		usuario = new Usuarios();
		usuario.setId(1L);
		usuario.setEmail("juan@polisport.cl");
		usuario.setPasswordHash("$2a$10$hash");
		usuario.setNombre("Juan");
		usuario.setApellido("Perez");
		usuario.setActivo(true);
		usuario.setFechaRegistro(LocalDateTime.now());
	}

	@Test
	void contextLoads() {
		assertNotNull(usuariosService);
	}

	@Test
	void shouldObtenerTodos() {
		when(usuariosRepository.findAll()).thenReturn(List.of(usuario));
		List<Usuarios> result = usuariosService.obtenerTodos();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}

	@Test
	void shouldObtenerPorId() {
		when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));
		Optional<Usuarios> result = usuariosService.obtenerPorId(1L);
		assertTrue(result.isPresent());
		assertEquals("juan@polisport.cl", result.get().getEmail());
	}

	@Test
	void shouldGuardar() {
		when(usuariosRepository.save(any(Usuarios.class))).thenReturn(usuario);
		Usuarios created = usuariosService.guardarUsuarios(usuario);
		assertNotNull(created);
		assertEquals("Juan", created.getNombre());
	}

	@Test
	void shouldReturnEmptyWhenNotFound() {
		when(usuariosRepository.findById(999L)).thenReturn(Optional.empty());
		Optional<Usuarios> result = usuariosService.obtenerPorId(999L);
		assertFalse(result.isPresent());
	}
}
