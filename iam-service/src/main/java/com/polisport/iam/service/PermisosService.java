package com.polisport.iam.service;

import com.polisport.iam.model.Permisos;
import com.polisport.iam.repository.PermisosRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermisosService {

	@Autowired
	private PermisosRepository permisosRepository;

	public List<Permisos> obtenerTodos() {
		log.info("Consultando la lista completa de permisos");
		return permisosRepository.findAll();
	}

	public Optional<Permisos> obtenerPorId(Long id) {
		log.info("Buscando permiso con ID: {}", id);
		return permisosRepository.findById(id);
	}

	public Permisos guardarPermisos(Permisos permisos) {
		log.info("Registrando o actualizando permiso: {}", permisos.getNombre());
		return permisosRepository.save(permisos);
	}

	public void eliminarPermisos(Long id) {
		log.warn("Eliminando de la base de datos el permiso con ID: {}", id);
		permisosRepository.deleteById(id);
	}
}

