package com.polisport.iam.service;

import com.polisport.iam.model.RolesUsuarios;
import com.polisport.iam.repository.RolesUsuariosRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolesUsuariosService {

	@Autowired
	private RolesUsuariosRepository rolesUsuariosRepository;

	public List<RolesUsuarios> obtenerTodos() {
		log.info("Consultando la lista completa de roles de usuarios");
		return rolesUsuariosRepository.findAll();
	}

	public Optional<RolesUsuarios> obtenerPorId(Long id) {
		log.info("Buscando relacion rol-usuario con ID: {}", id);
		return rolesUsuariosRepository.findById(id);
	}

	public RolesUsuarios guardarRolesUsuarios(RolesUsuarios rolesUsuarios) {
		log.info("Registrando o actualizando relacion rol-usuario");
		return rolesUsuariosRepository.save(rolesUsuarios);
	}

	public void eliminarRolesUsuarios(Long id) {
		log.warn("Eliminando de la base de datos la relacion rol-usuario con ID: {}", id);
		rolesUsuariosRepository.deleteById(id);
	}
}

