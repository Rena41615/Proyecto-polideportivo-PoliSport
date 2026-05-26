package com.polisport.nutricion.controller;

import com.polisport.common.dto.nutricion.PlanNutricionalDTO;
import com.polisport.common.dto.nutricion.PlanNutricionalCrearDTO;
import com.polisport.common.mapper.nutricion.PlanNutricionalMapper;
import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.service.PlanNutricionalService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nutricion")
@CrossOrigin(origins = "*")
public class NutricionController {

	private final PlanNutricionalService planNutricionalService;
	private final PlanNutricionalMapper planNutricionalMapper;

	public NutricionController(PlanNutricionalService planNutricionalService, PlanNutricionalMapper planNutricionalMapper) {
		this.planNutricionalService = planNutricionalService;
		this.planNutricionalMapper = planNutricionalMapper;
	}

	@GetMapping
	public ResponseEntity<?> mostrarPlanes() {
		try {
			List<PlanNutricional> planes = planNutricionalService.obtenerTodos();
			List<PlanNutricionalDTO> dtos = planes.stream()
					.map(planNutricionalMapper::entityToDTO)
					.toList();
			return ResponseEntity.ok(dtos);
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
				return ResponseEntity.ok(planNutricionalMapper.entityToDTO(planEncontrado));
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
	public ResponseEntity<?> guardarPlan(@Valid @RequestBody PlanNutricionalCrearDTO crearDTO) {
		try {
			PlanNutricional nuevoPlan = planNutricionalMapper.crearDTOToEntity(crearDTO);
			PlanNutricional planGuardado = planNutricionalService.guardarPlanNutricional(nuevoPlan);
			return ResponseEntity.status(HttpStatus.CREATED).body(planNutricionalMapper.entityToDTO(planGuardado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPlan(@PathVariable Long id, @Valid @RequestBody PlanNutricionalCrearDTO crearDTO) {
		try {
			PlanNutricional planExistente = planNutricionalService.obtenerPorId(id).orElse(null);

			if (planExistente != null) {
				PlanNutricional planActualizado = planNutricionalMapper.crearDTOToEntity(crearDTO);
				planActualizado.setId(id);
				PlanNutricional planActualizadoGuardado = planNutricionalService.guardarPlanNutricional(planActualizado);
				return ResponseEntity.ok(planNutricionalMapper.entityToDTO(planActualizadoGuardado));
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

