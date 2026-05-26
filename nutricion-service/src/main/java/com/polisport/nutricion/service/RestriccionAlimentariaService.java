package com.polisport.nutricion.service;

import com.polisport.nutricion.model.RestriccionAlimentaria;
import com.polisport.nutricion.repository.RestriccionAlimentariaRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestriccionAlimentariaService {

	@Autowired
	private RestriccionAlimentariaRepository restriccionAlimentariaRepository;

	public List<RestriccionAlimentaria> obtenerTodos() {
		log.info("Consultando la lista completa de restricciones alimentarias");
		return restriccionAlimentariaRepository.findAll();
	}

	public Optional<RestriccionAlimentaria> obtenerPorId(Long id) {
		log.info("Buscando restriccion alimentaria con ID: {}", id);
		return restriccionAlimentariaRepository.findById(id);
	}

	public RestriccionAlimentaria guardarRestriccionAlimentaria(RestriccionAlimentaria restriccionAlimentaria) {
		log.info("Registrando o actualizando restriccion alimentaria");
		return restriccionAlimentariaRepository.save(restriccionAlimentaria);
	}

	public void eliminarRestriccionAlimentaria(Long id) {
		log.warn("Eliminando de la base de datos la restriccion alimentaria con ID: {}", id);
		restriccionAlimentariaRepository.deleteById(id);
	}
}

