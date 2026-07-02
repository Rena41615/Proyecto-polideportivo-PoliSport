package com.polisport.staff;

import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.repository.MiembrosStaffRepository;
import com.polisport.staff.service.MiembrosStaffService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class StaffServiceApplicationTests {

	@Autowired
	private MiembrosStaffService miembrosStaffService;

	@MockBean
	private MiembrosStaffRepository miembrosStaffRepository;

	private MiembrosStaff miembro;

	@BeforeEach
	void setUp() {
		miembro = new MiembrosStaff();
		miembro.setId(1L);
		miembro.setRun(18456789);
		miembro.setDv("2");
		miembro.setNombre("Carlos");
		miembro.setApellido("Rodriguez");
		miembro.setDocumento("18456789-2");
		miembro.setTelefono("+56912345678");
		miembro.setEmail("carlos@polisport.cl");
		miembro.setPuesto("Entrenador Personal");
		miembro.setFechaIngreso(LocalDate.of(2023, 5, 10));
		miembro.setActivo(true);
		miembro.setObservaciones("Especialista en fitness");
	}

	@Test
	void contextLoads() {
		assertNotNull(miembrosStaffService);
	}

	@Test
	void shouldObtenerTodos() {
		when(miembrosStaffRepository.findAll()).thenReturn(List.of(miembro));
		List<MiembrosStaff> result = miembrosStaffService.obtenerTodos();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}

	@Test
	void shouldObtenerPorId() {
		when(miembrosStaffRepository.findById(1L)).thenReturn(Optional.of(miembro));
		Optional<MiembrosStaff> result = miembrosStaffService.obtenerPorId(1L);
		assertTrue(result.isPresent());
		assertEquals("Carlos", result.get().getNombre());
	}

	@Test
	void shouldGuardar() {
		when(miembrosStaffRepository.save(any(MiembrosStaff.class))).thenReturn(miembro);
		MiembrosStaff created = miembrosStaffService.guardarMiembrosStaff(miembro);
		assertNotNull(created);
		assertEquals("carlos@polisport.cl", created.getEmail());
	}

	@Test
	void shouldReturnEmptyWhenNotFound() {
		when(miembrosStaffRepository.findById(999L)).thenReturn(Optional.empty());
		Optional<MiembrosStaff> result = miembrosStaffService.obtenerPorId(999L);
		assertFalse(result.isPresent());
	}
}
