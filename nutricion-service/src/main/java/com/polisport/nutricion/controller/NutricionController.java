package com.polisport.nutricion.controller;

import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.service.PlanNutricionalService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nutricion")
public class NutricionController {

	private final PlanNutricionalService planNutricionalService;

	public NutricionController(PlanNutricionalService planNutricionalService) {
		this.planNutricionalService = planNutricionalService;
	}

	@GetMapping
	public ResponseEntity<?> mostrarPlanes() {
		try {
			List<PlanNutricional> planes = planNutricionalService.obtenerTodos();
			return ResponseEntity.ok(planes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los planes nutricionales: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			PlanNutricional planEncontrado = planNutricionalService.obtenerPorId(id).orElse(null);

			if (planEncontrado != null) {
				return ResponseEntity.ok(planEncontrado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Plan nutricional no encontrado con ID: " + id);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el plan nutricional: " + e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> guardarPlan(@Valid @RequestBody PlanNutricional nuevoPlan) {
		try {
			PlanNutricional planGuardado = planNutricionalService.guardarPlanNutricional(nuevoPlan);
			return ResponseEntity.status(HttpStatus.CREATED).body(planGuardado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos inválidos: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPlan(@PathVariable Long id, @Valid @RequestBody PlanNutricional planActualizado) {
		try {
			PlanNutricional planExistente = planNutricionalService.obtenerPorId(id).orElse(null);

			if (planExistente != null) {
				planActualizado.setId(id);
				PlanNutricional planActualizadoGuardado = planNutricionalService.guardarPlanNutricional(planActualizado);
				return ResponseEntity.ok(planActualizadoGuardado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Plan nutricional no encontrado con ID: " + id);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el plan nutricional: " + e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPlan(@PathVariable Long id) {
		try {
			PlanNutricional planExistente = planNutricionalService.obtenerPorId(id).orElse(null);

			if (planExistente != null) {
				planNutricionalService.eliminarPlanNutricional(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Plan nutricional no encontrado con ID: " + id);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el plan nutricional: " + e.getMessage());
		}
	}
}

