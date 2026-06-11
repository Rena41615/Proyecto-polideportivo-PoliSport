package com.polisport.nutricion.controller;

import com.polisport.nutricion.dto.PlanNutricionalDTO;
import com.polisport.nutricion.dto.PlanNutricionalCrearDTO;
import com.polisport.nutricion.mapper.PlanNutricionalMapper;
import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.service.PlanNutricionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nutricion")
@CrossOrigin(origins = "*")
@Tag(name = "Plan Nutricional", description = "Operaciones CRUD para planes nutricionales")
public class NutricionController {

	private final PlanNutricionalService planNutricionalService;
	private final PlanNutricionalMapper planNutricionalMapper;

	public NutricionController(PlanNutricionalService planNutricionalService, PlanNutricionalMapper planNutricionalMapper) {
		this.planNutricionalService = planNutricionalService;
		this.planNutricionalMapper = planNutricionalMapper;
	}

	@Operation(summary = "Listar todos los planes nutricionales", description = "Obtiene el listado completo de todos los planes nutricionales registrados en el sistema")
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

	@Operation(summary = "Buscar plan nutricional por ID", description = "Obtiene los detalles de un plan nutricional específico usando su identificador único")
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

	@Operation(summary = "Crear nuevo plan nutricional", description = "Crea e inserta un nuevo plan nutricional en el sistema con los datos proporcionados")
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

	@Operation(summary = "Actualizar plan nutricional", description = "Actualiza los datos de un plan nutricional existente usando su identificador único")
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

	@Operation(summary = "Eliminar plan nutricional", description = "Elimina un plan nutricional del sistema usando su identificador único")
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

