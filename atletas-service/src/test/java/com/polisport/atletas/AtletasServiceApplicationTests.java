package com.polisport.atletas;

import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.repository.AtletaRepository;
import com.polisport.atletas.service.AtletaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class AtletasServiceApplicationTests {

	@Autowired
	private AtletaService atletaService;

	@MockBean
	private AtletaRepository atletaRepository;

	private Atleta atleta;

	@BeforeEach
	void setUp() {
		atleta = new Atleta();
		atleta.setId(1L);
		atleta.setRunAtleta(20456789);
		atleta.setDvrunAtleta("K");
		atleta.setPrimerNombre("Juan");
		atleta.setPrimerApellido("Perez");
		atleta.setSegundoApellido("Garcia");
		atleta.setEmail("juan@test.cl");
		atleta.setFechaNacimiento(LocalDate.of(1998, 5, 15));
		atleta.setDeportePrincipal("Atletismo");
		atleta.setCategoria("Senior");
	}

	@Test
	void contextLoads() {
		assertNotNull(atletaService);
	}

	@Test
	void shouldCreateAtleta() {
		when(atletaRepository.save(any(Atleta.class))).thenReturn(atleta);
		Atleta created = atletaService.guardarAtleta(atleta);
		assertNotNull(created);
		assertEquals(20456789, created.getRunAtleta());
	}

	@Test
	void shouldFindAtletaByRun() {
		when(atletaRepository.findByRunAtleta(20456789)).thenReturn(Optional.of(atleta));
		Optional<Atleta> found = atletaService.obtenerPorRun(20456789);
		assertTrue(found.isPresent());
		assertEquals("Juan", found.get().getPrimerNombre());
	}

	@Test
	void shouldReturnEmptyWhenNotFound() {
		when(atletaRepository.findByRunAtleta(99999999)).thenReturn(Optional.empty());
		Optional<Atleta> found = atletaService.obtenerPorRun(99999999);
		assertFalse(found.isPresent());
	}
}
