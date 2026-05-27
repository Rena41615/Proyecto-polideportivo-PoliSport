package com.polisport.iam.service;

import com.polisport.iam.model.Rol;
import com.polisport.iam.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	public List<Rol> obtenerTodos() {
		log.info("Consultando la lista completa de roles");
		return rolRepository.findAll();
	}

	public Optional<Rol> obtenerPorId(Long id) {
		log.info("Buscando rol con ID: {}", id);
		return rolRepository.findById(id);
	}

	public Rol guardarRol(Rol rol) {
		log.info("Registrando o actualizando rol: {}", rol.getNombre());
		return rolRepository.save(rol);
	}

	public void eliminarRol(Long id) {
		log.warn("Eliminando de la base de datos el rol con ID: {}", id);
		rolRepository.deleteById(id);
	}
}

