package com.polisport.salud;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.repository.GestionMedicaRepository;
import com.polisport.salud.service.GestionMedicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class SaludServiceApplicationTests {

	@Autowired
	private GestionMedicaService gestionMedicaService;

	@MockBean
	private GestionMedicaRepository repository;

	private GestionMedica registro;

	@BeforeEach
	void setUp() {
		registro = new GestionMedica();
		registro.setId(1L);
		registro.setAtletaId(1L);
		registro.setTipoLesion("Esguince");
		registro.setFechaDiagnostico(LocalDate.now());
		registro.setDescripcion("Esguince de tobillo grado II");
	}

	@Test
	void contextLoads() {
		assertNotNull(gestionMedicaService);
	}

	@Test
	void shouldFindByAtletaId() {
		when(repository.findByAtletaId(1L)).thenReturn(List.of(registro));
		List<GestionMedica> result = gestionMedicaService.buscarPorAtletaId(1L);
		assertFalse(result.isEmpty());
		assertEquals("Esguince", result.get(0).getTipoLesion());
	}

	@Test
	void shouldReturnEmptyWhenNoRecords() {
		when(repository.findByAtletaId(999L)).thenReturn(List.of());
		List<GestionMedica> result = gestionMedicaService.buscarPorAtletaId(999L);
		assertTrue(result.isEmpty());
	}
}
