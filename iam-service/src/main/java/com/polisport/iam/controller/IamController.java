package com.polisport.iam.controller;

import com.polisport.iam.dto.*;
import com.polisport.iam.mapper.UsuariosMapper;
import com.polisport.iam.mapper.RolMapper;
import com.polisport.iam.model.Rol;
import com.polisport.iam.model.RolesUsuarios;
import com.polisport.iam.model.Usuarios;
import com.polisport.iam.service.RolService;
import com.polisport.iam.service.RolesUsuariosService;
import com.polisport.iam.service.UsuariosService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/iam")
@CrossOrigin(origins = "*")
public class IamController {

	private final UsuariosService usuariosService;
	private final RolService rolService;
	private final RolesUsuariosService rolesUsuariosService;
	private final UsuariosMapper usuariosMapper;
	private final RolMapper rolMapper;

	public IamController(UsuariosService usuariosService,
	                     RolService rolService,
	                     RolesUsuariosService rolesUsuariosService,
	                     UsuariosMapper usuariosMapper,
	                     RolMapper rolMapper) {
		this.usuariosService = usuariosService;
		this.rolService = rolService;
		this.rolesUsuariosService = rolesUsuariosService;
		this.usuariosMapper = usuariosMapper;
		this.rolMapper = rolMapper;
	}

	// --- SECCION DE USUARIOS ---

	@GetMapping("/usuarios")
	public ResponseEntity<?> mostrarUsuarios() {
		try {
			List<Usuarios> usuarios = usuariosService.obtenerTodos();
			List<UsuariosDTO> dtos = usuarios.stream()
					.map(usuariosMapper::entityToDTO)
					.toList();
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los usuarios: " + e.getMessage());
		}
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
		try {
			Usuarios usuarioEncontrado = usuariosService.obtenerPorId(id).orElse(null);
			if (usuarioEncontrado != null) {
				return ResponseEntity.ok(usuariosMapper.entityToDTO(usuarioEncontrado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Usuario no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el usuario: " + e.getMessage());
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<?> guardarUsuario(@Valid @RequestBody UsuariosCrearDTO crearDTO) {
		try {
			Usuarios nuevoUsuario = usuariosMapper.crearDTOToEntity(crearDTO);
			Usuarios guardado = usuariosService.guardarUsuarios(nuevoUsuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuariosMapper.entityToDTO(guardado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuariosCrearDTO crearDTO) {
		try {
			Usuarios usuarioExistente = usuariosService.obtenerPorId(id).orElse(null);
			if (usuarioExistente != null) {
				Usuarios usuarioActualizado = usuariosMapper.crearDTOToEntity(crearDTO);
				usuarioActualizado.setId(id);
				Usuarios guardado = usuariosService.guardarUsuarios(usuarioActualizado);
				return ResponseEntity.ok(usuariosMapper.entityToDTO(guardado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Usuario no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el usuario: " + e.getMessage());
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
		try {
			Usuarios usuarioExistente = usuariosService.obtenerPorId(id).orElse(null);
			if (usuarioExistente != null) {
				usuariosService.eliminarUsuarios(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Usuario no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el usuario: " + e.getMessage());
		}
	}

	// --- SECCION DE ROLES ---

	@GetMapping("/roles")
	public ResponseEntity<?> mostrarRoles() {
		try {
			List<Rol> roles = rolService.obtenerTodos();
			List<RolDTO> dtos = roles.stream()
					.map(rolMapper::entityToDTO)
					.toList();
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener los roles: " + e.getMessage());
		}
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<?> buscarRolPorId(@PathVariable Long id) {
		try {
			Rol rolEncontrado = rolService.obtenerPorId(id).orElse(null);
			if (rolEncontrado != null) {
				return ResponseEntity.ok(rolMapper.entityToDTO(rolEncontrado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el rol: " + e.getMessage());
		}
	}

	@PostMapping("/roles")
	public ResponseEntity<?> guardarRol(@Valid @RequestBody RolCrearDTO crearDTO) {
		try {
			Rol nuevoRol = rolMapper.crearDTOToEntity(crearDTO);
			Rol guardado = rolService.guardarRol(nuevoRol);
			return ResponseEntity.status(HttpStatus.CREATED).body(rolMapper.entityToDTO(guardado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<?> actualizarRol(@PathVariable Long id, @Valid @RequestBody RolCrearDTO crearDTO) {
		try {
			Rol rolExistente = rolService.obtenerPorId(id).orElse(null);
			if (rolExistente != null) {
				Rol rolActualizado = rolMapper.crearDTOToEntity(crearDTO);
				rolActualizado.setId(id);
				Rol guardado = rolService.guardarRol(rolActualizado);
				return ResponseEntity.ok(rolMapper.entityToDTO(guardado));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el rol: " + e.getMessage());
		}
	}

	@DeleteMapping("/roles/{id}")
	public ResponseEntity<?> eliminarRol(@PathVariable Long id) {
		try {
			Rol rolExistente = rolService.obtenerPorId(id).orElse(null);
			if (rolExistente != null) {
				rolService.eliminarRol(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Rol no encontrado con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el rol: " + e.getMessage());
		}
	}

	// --- SECCION DE RELACIONES ROLES-USUARIOS ---

	@GetMapping("/roles-usuarios")
	public ResponseEntity<?> mostrarRolesUsuarios() {
		try {
			List<RolesUsuarios> relaciones = rolesUsuariosService.obtenerTodos();
			return ResponseEntity.ok(relaciones);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener las relaciones usuario-rol: " + e.getMessage());
		}
	}

	@GetMapping("/roles-usuarios/{id}")
	public ResponseEntity<?> buscarRelacionPorId(@PathVariable Long id) {
		try {
			RolesUsuarios relacionEncontrada = rolesUsuariosService.obtenerPorId(id).orElse(null);
			if (relacionEncontrada != null) {
				return ResponseEntity.ok(relacionEncontrada);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Relacion usuario-rol no encontrada con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar la relacion usuario-rol: " + e.getMessage());
		}
	}

	@PostMapping("/roles-usuarios")
	public ResponseEntity<?> guardarRelacion(@Valid @RequestBody RolesUsuarios nuevaRelacion) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(rolesUsuariosService.guardarRolesUsuarios(nuevaRelacion));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Datos invalidos: " + e.getMessage());
		}
	}

	@PutMapping("/roles-usuarios/{id}")
	public ResponseEntity<?> actualizarRelacion(@PathVariable Long id, @Valid @RequestBody RolesUsuarios relacionActualizada) {
		try {
			RolesUsuarios relacionExistente = rolesUsuariosService.obtenerPorId(id).orElse(null);
			if (relacionExistente != null) {
				relacionActualizada.setId(id);
				return ResponseEntity.ok(rolesUsuariosService.guardarRolesUsuarios(relacionActualizada));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Relacion usuario-rol no encontrada con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar la relacion usuario-rol: " + e.getMessage());
		}
	}

	@DeleteMapping("/roles-usuarios/{id}")
	public ResponseEntity<?> eliminarRelacion(@PathVariable Long id) {
		try {
			RolesUsuarios relacionExistente = rolesUsuariosService.obtenerPorId(id).orElse(null);
			if (relacionExistente != null) {
				rolesUsuariosService.eliminarRolesUsuarios(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Relacion usuario-rol no encontrada con ID: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar la relacion usuario-rol: " + e.getMessage());
		}
	}
}