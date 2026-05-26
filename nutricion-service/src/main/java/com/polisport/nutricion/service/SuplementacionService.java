package com.polisport.nutricion.service;

import com.polisport.nutricion.model.Suplementacion;
import com.polisport.nutricion.repository.SuplementacionRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SuplementacionService {

	@Autowired
	private SuplementacionRepository suplementacionRepository;

	public List<Suplementacion> obtenerTodos() {
		log.info("Consultando la lista completa de suplementaciones");
		return suplementacionRepository.findAll();
	}

	public Optional<Suplementacion> obtenerPorId(Long id) {
		log.info("Buscando suplementacion con ID: {}", id);
		return suplementacionRepository.findById(id);
	}

	public Suplementacion guardarSuplementacion(Suplementacion suplementacion) {
		log.info("Registrando o actualizando suplementacion");
		return suplementacionRepository.save(suplementacion);
	}

	public void eliminarSuplementacion(Long id) {
		log.warn("Eliminando de la base de datos la suplementacion con ID: {}", id);
		suplementacionRepository.deleteById(id);
	}
}

