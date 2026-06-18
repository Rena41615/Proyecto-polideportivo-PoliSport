package com.polisport.nutricion;

import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.model.ObjetivoNutricional;
import com.polisport.nutricion.model.PlanEstado;
import com.polisport.nutricion.repository.PlanNutricionalRepository;
import com.polisport.nutricion.service.PlanNutricionalService;
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
class NutricionServiceApplicationTests {

	@Autowired
	private PlanNutricionalService planNutricionalService;

	@MockBean
	private PlanNutricionalRepository repository;

	private PlanNutricional plan;

	@BeforeEach
	void setUp() {
		plan = new PlanNutricional();
		plan.setId(1L);
		plan.setAtletaId(1L);
		plan.setDeporte("Futbol");
		plan.setObjetivo(ObjetivoNutricional.GANANCIA_MUSCULAR);
		plan.setFechaInicio(LocalDate.now());
		plan.setCaloriasDiariasGr(2500);
		plan.setProteinaGr(150);
		plan.setCarbohidratosGr(300);
		plan.setLipidosGr(80);
		plan.setEstado(PlanEstado.ACTIVO);
	}

	@Test
	void contextLoads() {
		assertNotNull(planNutricionalService);
	}

	@Test
	void shouldFindByAtletaId() {
		when(repository.findByAtletaId(1L)).thenReturn(List.of(plan));
		List<PlanNutricional> result = planNutricionalService.buscarPorAtletaId(1L);
		assertFalse(result.isEmpty());
		assertEquals("Futbol", result.get(0).getDeporte());
	}

	@Test
	void shouldCreatePlan() {
		when(repository.save(any(PlanNutricional.class))).thenReturn(plan);
		PlanNutricional created = planNutricionalService.guardarPlanNutricional(plan);
		assertNotNull(created);
		assertEquals(2500, created.getCaloriasDiariasGr());
	}
}
