package com.polisport.nutricion.service;

import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.repository.PlanNutricionalRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlanNutricionalService {

	@Autowired
	private PlanNutricionalRepository planNutricionalRepository;

	public List<PlanNutricional> obtenerTodos() {
		log.info("Consultando la lista completa de planes nutricionales");
		return planNutricionalRepository.findAll();
	}

	public Optional<PlanNutricional> obtenerPorId(Long id) {
		log.info("Buscando plan nutricional con ID: {}", id);
		return planNutricionalRepository.findById(id);
	}

	public PlanNutricional guardarPlanNutricional(PlanNutricional planNutricional) {
		log.info("Registrando o actualizando plan nutricional para atleta: {}", planNutricional.getAtletaId());
		return planNutricionalRepository.save(planNutricional);
	}

	public void eliminarPlanNutricional(Long id) {
		log.warn("Eliminando de la base de datos el plan nutricional con ID: {}", id);
		planNutricionalRepository.deleteById(id);
	}
}


