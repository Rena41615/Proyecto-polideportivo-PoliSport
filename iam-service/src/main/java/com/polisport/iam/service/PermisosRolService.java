package com.polisport.iam.service;

import com.polisport.iam.model.PermisosRol;
import com.polisport.iam.repository.PermisosRolRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermisosRolService {

	@Autowired
	private PermisosRolRepository permisosRolRepository;

	public List<PermisosRol> obtenerTodos() {
		log.info("Consultando la lista completa de permisos por rol");
		return permisosRolRepository.findAll();
	}

	public Optional<PermisosRol> obtenerPorId(Long id) {
		log.info("Buscando permiso por rol con ID: {}", id);
		return permisosRolRepository.findById(id);
	}

	public PermisosRol guardarPermisosRol(PermisosRol permisosRol) {
		log.info("Registrando o actualizando permiso por rol");
		return permisosRolRepository.save(permisosRol);
	}

	public void eliminarPermisosRol(Long id) {
		log.warn("Eliminando de la base de datos el permiso por rol con ID: {}", id);
		permisosRolRepository.deleteById(id);
	}
}

