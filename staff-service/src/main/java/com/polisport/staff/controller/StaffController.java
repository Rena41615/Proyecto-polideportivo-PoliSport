package com.polisport.staff.controller;

import com.polisport.staff.model.MiembrosPermisosStaff;
import com.polisport.staff.model.MiembrosRolStaff;
import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.service.MiembrosPermisosStaffService;
import com.polisport.staff.service.MiembrosRolStaffService;
import com.polisport.staff.service.MiembrosStaffService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

	private final MiembrosStaffService miembrosStaffService;
	private final MiembrosRolStaffService miembrosRolStaffService;
	private final MiembrosPermisosStaffService miembrosPermisosStaffService;

	public StaffController(MiembrosStaffService miembrosStaffService,
						  MiembrosRolStaffService miembrosRolStaffService,
						  MiembrosPermisosStaffService miembrosPermisosStaffService) {
		this.miembrosStaffService = miembrosStaffService;
		this.miembrosRolStaffService = miembrosRolStaffService;
		this.miembrosPermisosStaffService = miembrosPermisosStaffService;
	}

	@GetMapping("/miembros")
	public ResponseEntity<?> mostrarMiembros() {
		try {
			List<MiembrosStaff> miembros = miembrosStaffService.obtenerTodos();
			return ResponseEntity.ok(miembros);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los miembros del staff: " + e.getMessage());
		}
	}

	@GetMapping("/miembros/{id}")
	public ResponseEntity<?> buscarMiembroPorId(@PathVariable Long id) {
		try {
			MiembrosStaff miembroEncontrado = miembrosStaffService.obtenerPorId(id).orElse(null);
			if (miembroEncontrado != null) {
				return ResponseEntity.ok(miembroEncontrado);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Miembro del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el miembro del staff: " + e.getMessage());
		}
	}

	@PostMapping("/miembros")
	public ResponseEntity<?> guardarMiembro(@Valid @RequestBody MiembrosStaff nuevoMiembro) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(miembrosStaffService.guardarMiembrosStaff(nuevoMiembro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos inválidos: " + e.getMessage());
		}
	}

	@PutMapping("/miembros/{id}")
	public ResponseEntity<?> actualizarMiembro(@PathVariable Long id, @Valid @RequestBody MiembrosStaff miembroActualizado) {
		try {
			MiembrosStaff miembroExistente = miembrosStaffService.obtenerPorId(id).orElse(null);
			if (miembroExistente != null) {
				miembroActualizado.setId(id);
				return ResponseEntity.ok(miembrosStaffService.guardarMiembrosStaff(miembroActualizado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Miembro del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el miembro del staff: " + e.getMessage());
		}
	}

	@DeleteMapping("/miembros/{id}")
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
	public ResponseEntity<?> mostrarRolesStaff() {
		try {
			List<MiembrosRolStaff> roles = miembrosRolStaffService.obtenerTodos();
			return ResponseEntity.ok(roles);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los roles del staff: " + e.getMessage());
		}
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<?> buscarRolStaffPorId(@PathVariable Long id) {
		try {
			MiembrosRolStaff rolEncontrado = miembrosRolStaffService.obtenerPorId(id).orElse(null);
			if (rolEncontrado != null) {
				return ResponseEntity.ok(rolEncontrado);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el rol del staff: " + e.getMessage());
		}
	}

	@PostMapping("/roles")
	public ResponseEntity<?> guardarRolStaff(@Valid @RequestBody MiembrosRolStaff nuevoRol) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(miembrosRolStaffService.guardarMiembrosRolStaff(nuevoRol));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos inválidos: " + e.getMessage());
		}
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<?> actualizarRolStaff(@PathVariable Long id, @Valid @RequestBody MiembrosRolStaff rolActualizado) {
		try {
			MiembrosRolStaff rolExistente = miembrosRolStaffService.obtenerPorId(id).orElse(null);
			if (rolExistente != null) {
				rolActualizado.setId(id);
				return ResponseEntity.ok(miembrosRolStaffService.guardarMiembrosRolStaff(rolActualizado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol del staff no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el rol del staff: " + e.getMessage());
		}
	}

	@DeleteMapping("/roles/{id}")
	public ResponseEntity<?> eliminarRolStaff(@PathVariable Long id) {
		try {
			MiembrosRolStaff rolExistente = miembrosRolStaffService.obtenerPorId(id).orElse(null);
			if (rolExistente != null) {
				miembrosRolStaffService.eliminarMiembrosRolStaff(id);
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
	public ResponseEntity<?> guardarPermisoStaff(@Valid @RequestBody MiembrosPermisosStaff nuevoPermiso) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(miembrosPermisosStaffService.guardarMiembrosPermisosStaff(nuevoPermiso));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos inválidos: " + e.getMessage());
		}
	}

	@PutMapping("/permisos/{id}")
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

