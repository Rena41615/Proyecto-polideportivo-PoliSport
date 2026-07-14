package com.polisport.iam.service;

import com.polisport.iam.model.PermisosRol;
import com.polisport.iam.repository.PermisosRolRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio IAM encargado de gestionar la relacion entre {@code Permisos} y {@code Rol}
 * (tabla intermedia {@code permisos_rol}), es decir, que permisos tiene asignado cada rol.
 *
 * <p>Expone las operaciones CRUD basicas sobre {@link PermisosRolRepository}, delegando toda la
 * persistencia en Spring Data JPA.</p>
 */
@Service
@Slf4j
public class PermisosRolService {

	@Autowired
	private PermisosRolRepository permisosRolRepository;

	/**
	 * Obtiene la lista completa de asignaciones permiso-rol registradas.
	 *
	 * @return lista con todas las entidades {@link PermisosRol}
	 */
	public List<PermisosRol> obtenerTodos() {
		log.info("Consultando la lista completa de permisos por rol");
		return permisosRolRepository.findAll();
	}

	/**
	 * Busca una asignacion permiso-rol por su identificador.
	 *
	 * @param id identificador de la relacion permiso-rol
	 * @return un {@link Optional} con la entidad encontrada, o vacio si no existe
	 */
	public Optional<PermisosRol> obtenerPorId(Long id) {
		log.info("Buscando permiso por rol con ID: {}", id);
		return permisosRolRepository.findById(id);
	}

	/**
	 * Crea o actualiza una asignacion permiso-rol.
	 *
	 * @param permisosRol entidad a registrar o actualizar
	 * @return la entidad persistida, con el ID asignado por la base de datos
	 */
	public PermisosRol guardarPermisosRol(PermisosRol permisosRol) {
		log.info("Registrando o actualizando permiso por rol");
		return permisosRolRepository.save(permisosRol);
	}

	/**
	 * Elimina una asignacion permiso-rol por su identificador.
	 *
	 * @param id identificador de la relacion permiso-rol a eliminar
	 */
	public void eliminarPermisosRol(Long id) {
		log.warn("Eliminando de la base de datos el permiso por rol con ID: {}", id);
		permisosRolRepository.deleteById(id);
	}
}

