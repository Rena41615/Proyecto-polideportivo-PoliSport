package com.polisport.iam.service;

import com.polisport.iam.model.RolesUsuarios;
import com.polisport.iam.repository.RolesUsuariosRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio IAM encargado de gestionar la relacion entre {@code Usuarios} y {@code Rol}
 * (tabla intermedia {@code roles_usuarios}), es decir, que roles tiene asignado cada usuario.
 *
 * <p>Delega toda la persistencia en {@link RolesUsuariosRepository} (Spring Data JPA).</p>
 */
@Service
@Slf4j
public class RolesUsuariosService {

	@Autowired
	private RolesUsuariosRepository rolesUsuariosRepository;

	/**
	 * Obtiene la lista completa de asignaciones rol-usuario registradas.
	 *
	 * @return lista con todas las entidades {@link RolesUsuarios}
	 */
	public List<RolesUsuarios> obtenerTodos() {
		log.info("Consultando la lista completa de roles de usuarios");
		return rolesUsuariosRepository.findAll();
	}

	/**
	 * Busca una asignacion rol-usuario por su identificador.
	 *
	 * @param id identificador de la relacion rol-usuario
	 * @return un {@link Optional} con la entidad encontrada, o vacio si no existe
	 */
	public Optional<RolesUsuarios> obtenerPorId(Long id) {
		log.info("Buscando relacion rol-usuario con ID: {}", id);
		return rolesUsuariosRepository.findById(id);
	}

	/**
	 * Crea o actualiza una asignacion rol-usuario.
	 *
	 * @param rolesUsuarios entidad a registrar o actualizar
	 * @return la entidad persistida, con el ID asignado por la base de datos
	 */
	public RolesUsuarios guardarRolesUsuarios(RolesUsuarios rolesUsuarios) {
		log.info("Registrando o actualizando relacion rol-usuario");
		return rolesUsuariosRepository.save(rolesUsuarios);
	}

	/**
	 * Elimina una asignacion rol-usuario por su identificador.
	 *
	 * @param id identificador de la relacion rol-usuario a eliminar
	 */
	public void eliminarRolesUsuarios(Long id) {
		log.warn("Eliminando de la base de datos la relacion rol-usuario con ID: {}", id);
		rolesUsuariosRepository.deleteById(id);
	}
}

