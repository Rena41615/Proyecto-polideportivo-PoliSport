package com.polisport.iam.service;

import com.polisport.iam.model.Permisos;
import com.polisport.iam.repository.PermisosRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio IAM encargado de la gestion CRUD de los {@link Permisos} del sistema,
 * es decir, las acciones puntuales que pueden asignarse a un rol (por ejemplo, "CREAR_USUARIO").
 *
 * <p>Delega toda la persistencia en {@link PermisosRepository} (Spring Data JPA).</p>
 */
@Service
@Slf4j
public class PermisosService {

	@Autowired
	private PermisosRepository permisosRepository;

	/**
	 * Obtiene la lista completa de permisos registrados en el sistema.
	 *
	 * @return lista con todas las entidades {@link Permisos}
	 */
	public List<Permisos> obtenerTodos() {
		log.info("Consultando la lista completa de permisos");
		return permisosRepository.findAll();
	}

	/**
	 * Busca un permiso por su identificador.
	 *
	 * @param id identificador del permiso
	 * @return un {@link Optional} con el permiso encontrado, o vacio si no existe
	 */
	public Optional<Permisos> obtenerPorId(Long id) {
		log.info("Buscando permiso con ID: {}", id);
		return permisosRepository.findById(id);
	}

	/**
	 * Crea o actualiza un permiso.
	 *
	 * @param permisos entidad a registrar o actualizar
	 * @return la entidad persistida, con el ID asignado por la base de datos
	 */
	public Permisos guardarPermisos(Permisos permisos) {
		log.info("Registrando o actualizando permiso: {}", permisos.getNombre());
		return permisosRepository.save(permisos);
	}

	/**
	 * Elimina un permiso por su identificador.
	 *
	 * @param id identificador del permiso a eliminar
	 */
	public void eliminarPermisos(Long id) {
		log.warn("Eliminando de la base de datos el permiso con ID: {}", id);
		permisosRepository.deleteById(id);
	}
}

