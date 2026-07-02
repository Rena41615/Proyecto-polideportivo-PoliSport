package com.polisport.inventario;

import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.repository.InstalacionRepository;
import com.polisport.inventario.repository.InventarioRepository;
import com.polisport.inventario.service.InstalacionService;
import com.polisport.inventario.service.InventarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class InventarioServiceApplicationTests {

	@Autowired
	private InventarioService inventarioService;

	@Autowired
	private InstalacionService instalacionService;

	@MockBean
	private InventarioRepository inventarioRepository;

	@MockBean
	private InstalacionRepository instalacionRepository;

	private Inventario equipo;
	private Instalacion instalacion;

	@BeforeEach
	void setUp() {
		equipo = new Inventario();
		equipo.setId(1L);
		equipo.setNombre("Balon de futbol");
		equipo.setDescripcion("Balon oficial FIFA tamano 5");
		equipo.setUbicacion("Bodega B, Estante 3");
		equipo.setCantidad(15);
		equipo.setEstado("DISPONIBLE");
		equipo.setInstalacionId(1L);

		instalacion = new Instalacion();
		instalacion.setId(1L);
		instalacion.setNombre("Cancha de Futbol Principal");
		instalacion.setDescripcion("Cancha de futbol 11 con iluminacion nocturna");
		instalacion.setUbicacion("Sector Norte del Complejo");
		instalacion.setTipo("Futbol");
		instalacion.setCapacidad(100);
		instalacion.setDisponible(true);
		instalacion.setEstado("OPERATIVA");
	}

	@Test
	void contextLoads() {
		assertNotNull(inventarioService);
		assertNotNull(instalacionService);
	}

	@Test
	void shouldListarInventario() {
		when(inventarioRepository.findAll()).thenReturn(List.of(equipo));
		List<Inventario> result = inventarioService.listar();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}

	@Test
	void shouldCrearInventario() {
		when(inventarioRepository.save(any(Inventario.class))).thenReturn(equipo);
		Inventario created = inventarioService.crear(equipo);
		assertNotNull(created);
		assertEquals("Balon de futbol", created.getNombre());
	}

	@Test
	void shouldListarInstalaciones() {
		when(instalacionRepository.findAll()).thenReturn(List.of(instalacion));
		List<Instalacion> result = instalacionService.listar();
		assertFalse(result.isEmpty());
		assertEquals("Cancha de Futbol Principal", result.get(0).getNombre());
	}

	@Test
	void shouldCrearInstalacion() {
		when(instalacionRepository.save(any(Instalacion.class))).thenReturn(instalacion);
		Instalacion created = instalacionService.crear(instalacion);
		assertNotNull(created);
		assertTrue(created.isDisponible());
	}

	@Test
	void shouldFindInventarioById() {
		when(inventarioRepository.findById(1L)).thenReturn(Optional.of(equipo));
		Optional<Inventario> result = inventarioService.buscarPorId(1L);
		assertTrue(result.isPresent());
		assertEquals(15, result.get().getCantidad());
	}
}
