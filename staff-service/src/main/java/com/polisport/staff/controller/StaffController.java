package com.polisport.staff.controller;

import com.polisport.staff.dto.*;
import com.polisport.staff.mapper.MiembrosStaffMapper;
import com.polisport.staff.mapper.RolStaffMapper;
import com.polisport.staff.model.MiembrosPermisosStaff;
import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.model.RolStaff;
import com.polisport.staff.repository.RolStaffRepository;
import com.polisport.staff.service.MiembrosPermisosStaffService;
import com.polisport.staff.service.MiembrosStaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "*")
@Tag(name = "Staff - Gestión de Personal", description = "Gestión de miembros del staff, roles de personal y sus permisos")
public class StaffController {

	private final MiembrosStaffService miembrosStaffService;
	private final MiembrosPermisosStaffService miembrosPermisosStaffService;
	private final MiembrosStaffMapper miembrosStaffMapper;
	private final RolStaffMapper rolStaffMapper;
	private final RolStaffRepository rolStaffRepository;

	public StaffController(MiembrosStaffService miembrosStaffService,
						  MiembrosPermisosStaffService miembrosPermisosStaffService,
						  MiembrosStaffMapper miembrosStaffMapper,
						  RolStaffMapper rolStaffMapper,
						  RolStaffRepository rolStaffRepository) {
		this.miembrosStaffService = miembrosStaffService;
		this.miembrosPermisosStaffService = miembrosPermisosStaffService;
		this.miembrosStaffMapper = miembrosStaffMapper;
		this.rolStaffMapper = rolStaffMapper;
		this.rolStaffRepository = rolStaffRepository;
	}

	@GetMapping("/miembros")
	@Operation(
		summary = "Listar todos los miembros del staff",
		description = "Obtiene la lista completa de todos los miembros del personal registrados"
	)
	public ResponseEntity<?> mostrarMiembros() {
		try {
			List<MiembrosStaff> miembros = miembrosStaffService.obtenerTodos();
			List<MiembrosStaffDTO> dtos = miembros.stream()
					.map(miembrosStaffMapper::entityToDTO)
					.toList();
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los miembros del staff: " + e.getMessage());
		}
	}

	@GetMapping("/miembros/{id}")
	@Operation(
		summary = "Obtener miembro del staff por ID",
		description = "Busca y retorna un miembro del personal específico por su identificador único"
	)
	public ResponseEntity<?> buscarMiembroPorId(@PathVariable Long id) {
		try {
			MiembrosStaff miembroEncontrado = miembrosStaffService.obtenerPorId(id).orElse(null);
			if (miembroEncontrado != null) {
				return ResponseEntity.ok(miembrosStaffMapper.entityToDTO(miembroEncontrado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Miembro del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el miembro del staff: " + e.getMessage());
		}
	}

	@PostMapping("/miembros")
	@Operation(
		summary = "Crear nuevo miembro del staff",
		description = "Crea un nuevo miembro del personal en el sistema con los datos proporcionados"
	)
	public ResponseEntity<?> guardarMiembro(@Valid @RequestBody MiembrosStaffCrearDTO crearDTO) {
		try {
			MiembrosStaff nuevoMiembro = miembrosStaffMapper.crearDTOToEntity(crearDTO);
			MiembrosStaff guardado = miembrosStaffService.guardarMiembrosStaff(nuevoMiembro);
			return ResponseEntity.status(HttpStatus.CREATED).body(miembrosStaffMapper.entityToDTO(guardado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/miembros/{id}")
	@Operation(
		summary = "Actualizar miembro del staff",
		description = "Actualiza los datos de un miembro del personal existente identificado por su ID"
	)
	public ResponseEntity<?> actualizarMiembro(@PathVariable Long id, @Valid @RequestBody MiembrosStaffCrearDTO crearDTO) {
		try {
			MiembrosStaff miembroExistente = miembrosStaffService.obtenerPorId(id).orElse(null);
			if (miembroExistente != null) {
				MiembrosStaff miembroActualizado = miembrosStaffMapper.crearDTOToEntity(crearDTO);
				miembroActualizado.setId(id);
				MiembrosStaff guardado = miembrosStaffService.guardarMiembrosStaff(miembroActualizado);
				return ResponseEntity.ok(miembrosStaffMapper.entityToDTO(guardado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Miembro del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el miembro del staff: " + e.getMessage());
		}
	}

	@DeleteMapping("/miembros/{id}")
	@Operation(
		summary = "Eliminar miembro del staff",
		description = "Elimina un miembro del personal del sistema identificado por su ID"
	)
	public ResponseEntity<?> eliminarMiembro(@PathVariable Long id) {
		try {
			MiembrosStaff miembroExistente = miembrosStaffService.obtenerPorId(id).orElse(null);
			if (miembroExistente != null) {
				miembrosStaffService.eliminarMiembrosStaff(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Miembro del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el miembro del staff: " + e.getMessage());
		}
	}

	@GetMapping("/roles")
	@Operation(
		summary = "Listar todos los roles del staff",
		description = "Obtiene la lista completa de todos los roles disponibles para el personal"
	)
	public ResponseEntity<?> mostrarRolesStaff() {
		try {
			List<RolStaff> roles = rolStaffRepository.findAll();
			List<RolStaffDTO> dtos = roles.stream()
					.map(rolStaffMapper::entityToDTO)
					.toList();
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los roles del staff: " + e.getMessage());
		}
	}

	@GetMapping("/roles/{id}")
	@Operation(
		summary = "Obtener rol del staff por ID",
		description = "Busca y retorna un rol del personal específico por su identificador único"
	)
	public ResponseEntity<?> buscarRolStaffPorId(@PathVariable Long id) {
		try {
			RolStaff rolEncontrado = rolStaffRepository.findById(id).orElse(null);
			if (rolEncontrado != null) {
				return ResponseEntity.ok(rolStaffMapper.entityToDTO(rolEncontrado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el rol del staff: " + e.getMessage());
		}
	}

	@PostMapping("/roles")
	@Operation(
		summary = "Crear nuevo rol del staff",
		description = "Crea un nuevo rol para el personal en el sistema con los datos proporcionados"
	)
	public ResponseEntity<?> guardarRolStaff(@Valid @RequestBody RolStaffCrearDTO crearDTO) {
		try {
			RolStaff nuevoRol = rolStaffMapper.crearDTOToEntity(crearDTO);
			RolStaff guardado = rolStaffRepository.save(nuevoRol);
			return ResponseEntity.status(HttpStatus.CREATED).body(rolStaffMapper.entityToDTO(guardado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/roles/{id}")
	@Operation(
		summary = "Actualizar rol del staff",
		description = "Actualiza los datos de un rol del personal existente identificado por su ID"
	)
	public ResponseEntity<?> actualizarRolStaff(@PathVariable Long id, @Valid @RequestBody RolStaffCrearDTO crearDTO) {
		try {
			RolStaff rolExistente = rolStaffRepository.findById(id).orElse(null);
			if (rolExistente != null) {
				RolStaff rolActualizado = rolStaffMapper.crearDTOToEntity(crearDTO);
				rolActualizado.setId(id);
				RolStaff guardado = rolStaffRepository.save(rolActualizado);
				return ResponseEntity.ok(rolStaffMapper.entityToDTO(guardado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el rol del staff: " + e.getMessage());
		}
	}

	@DeleteMapping("/roles/{id}")
	@Operation(
		summary = "Eliminar rol del staff",
		description = "Elimina un rol del personal del sistema identificado por su ID"
	)
	public ResponseEntity<?> eliminarRolStaff(@PathVariable Long id) {
		try {
			RolStaff rolExistente = rolStaffRepository.findById(id).orElse(null);
			if (rolExistente != null) {
				rolStaffRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el rol del staff: " + e.getMessage());
		}
	}

	@GetMapping("/permisos")
	@Operation(
		summary = "Listar todos los permisos del staff",
		description = "Obtiene la lista completa de todos los permisos disponibles para el personal"
	)
	public ResponseEntity<?> mostrarPermisosStaff() {
		try {
			List<MiembrosPermisosStaff> permisos = miembrosPermisosStaffService.obtenerTodos();
			return ResponseEntity.ok(permisos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los permisos del staff: " + e.getMessage());
		}
	}

	@GetMapping("/permisos/{id}")
	@Operation(
		summary = "Obtener permiso del staff por ID",
		description = "Busca y retorna un permiso del personal específico por su identificador único"
	)
	public ResponseEntity<?> buscarPermisoStaffPorId(@PathVariable Long id) {
		try {
			MiembrosPermisosStaff permisoEncontrado = miembrosPermisosStaffService.obtenerPorId(id).orElse(null);
			if (permisoEncontrado != null) {
				return ResponseEntity.ok(permisoEncontrado);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Permiso del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el permiso del staff: " + e.getMessage());
		}
	}

	@PostMapping("/permisos")
	@Operation(
		summary = "Crear nuevo permiso del staff",
		description = "Crea un nuevo permiso para el personal en el sistema con los datos proporcionados"
	)
	public ResponseEntity<?> guardarPermisoStaff(@Valid @RequestBody MiembrosPermisosStaff nuevoPermiso) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(miembrosPermisosStaffService.guardarMiembrosPermisosStaff(nuevoPermiso));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/permisos/{id}")
	@Operation(
		summary = "Actualizar permiso del staff",
		description = "Actualiza los datos de un permiso del personal existente identificado por su ID"
	)
	public ResponseEntity<?> actualizarPermisoStaff(@PathVariable Long id, @Valid @RequestBody MiembrosPermisosStaff permisoActualizado) {
		try {
			MiembrosPermisosStaff permisoExistente = miembrosPermisosStaffService.obtenerPorId(id).orElse(null);
			if (permisoExistente != null) {
				permisoActualizado.setId(id);
				return ResponseEntity.ok(miembrosPermisosStaffService.guardarMiembrosPermisosStaff(permisoActualizado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Permiso del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el permiso del staff: " + e.getMessage());
		}
	}

	@DeleteMapping("/permisos/{id}")
	@Operation(
		summary = "Eliminar permiso del staff",
		description = "Elimina un permiso del personal del sistema identificado por su ID"
	)
	public ResponseEntity<?> eliminarPermisoStaff(@PathVariable Long id) {
		try {
			MiembrosPermisosStaff permisoExistente = miembrosPermisosStaffService.obtenerPorId(id).orElse(null);
			if (permisoExistente != null) {
				miembrosPermisosStaffService.eliminarMiembrosPermisosStaff(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Permiso del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el permiso del staff: " + e.getMessage());
		}
	}
}
