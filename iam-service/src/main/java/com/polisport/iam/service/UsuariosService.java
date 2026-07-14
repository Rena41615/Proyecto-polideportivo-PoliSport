package com.polisport.iam.service;

import com.polisport.iam.model.Usuarios;
import com.polisport.iam.repository.UsuariosRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio IAM encargado de la gestion CRUD de los {@link Usuarios} del sistema
 * PoliSport (cuentas de acceso, credenciales y estado de activacion).
 *
 * <p>Delega toda la persistencia en {@link UsuariosRepository} (Spring Data JPA).</p>
 */
@Service
@Slf4j
public class UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	/**
	 * Obtiene la lista completa de usuarios registrados en el sistema.
	 *
	 * @return lista con todas las entidades {@link Usuarios}
	 */
	public List<Usuarios> obtenerTodos() {
		log.info("Consultando la lista completa de usuarios");
		return usuariosRepository.findAll();
	}

	/**
	 * Busca un usuario por su identificador.
	 *
	 * @param id identificador del usuario
	 * @return un {@link Optional} con el usuario encontrado, o vacio si no existe
	 */
	public Optional<Usuarios> obtenerPorId(Long id) {
		log.info("Buscando usuario con ID: {}", id);
		return usuariosRepository.findById(id);
	}

	/**
	 * Crea o actualiza un usuario.
	 *
	 * @param usuarios entidad a registrar o actualizar
	 * @return la entidad persistida, con el ID asignado por la base de datos
	 */
	public Usuarios guardarUsuarios(Usuarios usuarios) {
		log.info("Registrando o actualizando usuario con email: {}", usuarios.getEmail());
		return usuariosRepository.save(usuarios);
	}

	/**
	 * Elimina un usuario por su identificador.
	 *
	 * @param id identificador del usuario a eliminar
	 */
	public void eliminarUsuarios(Long id) {
		log.warn("Eliminando de la base de datos el usuario con ID: {}", id);
		usuariosRepository.deleteById(id);
	}
}

