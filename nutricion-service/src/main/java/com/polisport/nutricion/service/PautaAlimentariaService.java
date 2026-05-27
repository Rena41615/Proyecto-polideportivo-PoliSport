package com.polisport.nutricion.service;

import com.polisport.nutricion.model.PautaAlimentaria;
import com.polisport.nutricion.repository.PautaAlimentariaRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PautaAlimentariaService {

	@Autowired
	private PautaAlimentariaRepository pautaAlimentariaRepository;

	public List<PautaAlimentaria> obtenerTodos() {
		log.info("Consultando la lista completa de pautas alimentarias");
		return pautaAlimentariaRepository.findAll();
	}

	public Optional<PautaAlimentaria> obtenerPorId(Long id) {
		log.info("Buscando pauta alimentaria con ID: {}", id);
		return pautaAlimentariaRepository.findById(id);
	}

	public PautaAlimentaria guardarPautaAlimentaria(PautaAlimentaria pautaAlimentaria) {
		log.info("Registrando o actualizando pauta alimentaria");
		return pautaAlimentariaRepository.save(pautaAlimentaria);
	}

	public void eliminarPautaAlimentaria(Long id) {
		log.warn("Eliminando de la base de datos la pauta alimentaria con ID: {}", id);
		pautaAlimentariaRepository.deleteById(id);
	}
}

