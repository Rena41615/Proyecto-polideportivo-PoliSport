package com.polisport.iam.service;

import com.polisport.iam.model.Rol;
import com.polisport.iam.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio IAM encargado de la gestion CRUD de los {@link Rol} de acceso del sistema
 * (por ejemplo, "Administrador", "Entrenador"), que agrupan permisos y se asignan a usuarios.
 *
 * <p>Delega toda la persistencia en {@link RolRepository} (Spring Data JPA).</p>
 */
@Service
@Slf4j
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	/**
	 * Obtiene la lista completa de roles registrados en el sistema.
	 *
	 * @return lista con todas las entidades {@link Rol}
	 */
	public List<Rol> obtenerTodos() {
		log.info("Consultando la lista completa de roles");
		return rolRepository.findAll();
	}

	/**
	 * Busca un rol por su identificador.
	 *
	 * @param id identificador del rol
	 * @return un {@link Optional} con el rol encontrado, o vacio si no existe
	 */
	public Optional<Rol> obtenerPorId(Long id) {
		log.info("Buscando rol con ID: {}", id);
		return rolRepository.findById(id);
	}

	/**
	 * Crea o actualiza un rol.
	 *
	 * @param rol entidad a registrar o actualizar
	 * @return la entidad persistida, con el ID asignado por la base de datos
	 */
	public Rol guardarRol(Rol rol) {
		log.info("Registrando o actualizando rol: {}", rol.getNombre());
		return rolRepository.save(rol);
	}

	/**
	 * Elimina un rol por su identificador.
	 *
	 * @param id identificador del rol a eliminar
	 */
	public void eliminarRol(Long id) {
		log.warn("Eliminando de la base de datos el rol con ID: {}", id);
		rolRepository.deleteById(id);
	}
}

