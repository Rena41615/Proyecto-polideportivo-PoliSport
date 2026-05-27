package com.polisport.iam.service;

import com.polisport.iam.model.Usuarios;
import com.polisport.iam.repository.UsuariosRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	public List<Usuarios> obtenerTodos() {
		log.info("Consultando la lista completa de usuarios");
		return usuariosRepository.findAll();
	}

	public Optional<Usuarios> obtenerPorId(Long id) {
		log.info("Buscando usuario con ID: {}", id);
		return usuariosRepository.findById(id);
	}

	public Usuarios guardarUsuarios(Usuarios usuarios) {
		log.info("Registrando o actualizando usuario con email: {}", usuarios.getEmail());
		return usuariosRepository.save(usuarios);
	}

	public void eliminarUsuarios(Long id) {
		log.warn("Eliminando de la base de datos el usuario con ID: {}", id);
		usuariosRepository.deleteById(id);
	}
}

