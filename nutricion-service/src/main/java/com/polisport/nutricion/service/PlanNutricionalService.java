package com.polisport.nutricion.service;

import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.nutricion.repository.PlanNutricionalRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion de los planes nutricionales.
 * <p>
 * Un plan nutricional es la entidad central del microservicio de nutricion:
 * agrupa las restricciones alimentarias, pautas alimentarias y suplementaciones
 * de un atleta. Esta clase administra el ciclo de vida de estos planes y se
 * encarga de mantener consistente la relacion bidireccional con sus colecciones
 * hijas antes de persistir.
 */
@Service
@Slf4j
public class PlanNutricionalService {

	@Autowired
	private PlanNutricionalRepository planNutricionalRepository;

	/**
	 * Obtiene la lista completa de planes nutricionales registrados.
	 *
	 * @return lista con todos los planes nutricionales existentes; puede estar vacia
	 */
	public List<PlanNutricional> obtenerTodos() {
		log.info("Consultando la lista completa de planes nutricionales");
		return planNutricionalRepository.findAll();
	}

	/**
	 * Busca un plan nutricional por su identificador.
	 *
	 * @param id identificador unico del plan nutricional
	 * @return un {@link Optional} con el plan encontrado, o vacio si no existe
	 */
	public Optional<PlanNutricional> obtenerPorId(Long id) {
		log.info("Buscando plan nutricional con ID: {}", id);
		return planNutricionalRepository.findById(id);
	}

	/**
	 * Crea o actualiza un plan nutricional. Antes de persistir, sincroniza la
	 * relacion bidireccional asignando el propio plan como referencia en cada
	 * una de sus restricciones, pautas y suplementos, para que la clave foranea
	 * quede correctamente establecida en cascada.
	 *
	 * @param planNutricional entidad a persistir, con sus colecciones asociadas (opcionales)
	 * @return la entidad persistida, con su ID generado si se trataba de un alta
	 */
	public PlanNutricional guardarPlanNutricional(PlanNutricional planNutricional) {
		log.info("Registrando o actualizando plan nutricional para atleta: {}", planNutricional.getAtletaId());

		// Se enlaza cada restriccion con el plan padre para que la FK se persista via cascada
		if (planNutricional.getRestricciones() != null) {
			planNutricional.getRestricciones().forEach(r -> r.setPlan(planNutricional));
		}
		// Se enlaza cada pauta alimentaria con el plan padre
		if (planNutricional.getPautas() != null) {
			planNutricional.getPautas().forEach(p -> p.setPlan(planNutricional));
		}
		// Se enlaza cada suplemento con el plan padre
		if (planNutricional.getSuplementos() != null) {
			planNutricional.getSuplementos().forEach(s -> s.setPlan(planNutricional));
		}

		return planNutricionalRepository.save(planNutricional);
	}

	/**
	 * Elimina un plan nutricional por su identificador. Al estar configurada la
	 * relacion con {@code orphanRemoval} en cascada, sus restricciones, pautas y
	 * suplementos asociados tambien se eliminan.
	 *
	 * @param id identificador unico del plan nutricional a eliminar
	 */
	public void eliminarPlanNutricional(Long id) {
		log.warn("Eliminando de la base de datos el plan nutricional con ID: {}", id);
		planNutricionalRepository.deleteById(id);
	}

	/**
	 * Busca todos los planes nutricionales asociados a un atleta especifico.
	 *
	 * @param atletaId identificador del atleta
	 * @return lista de planes nutricionales del atleta; puede estar vacia si no tiene ninguno
	 */
	public List<PlanNutricional> buscarPorAtletaId(Long atletaId) {
		log.info("Buscando planes nutricionales para el atleta ID: {}", atletaId);
		return planNutricionalRepository.findByAtletaId(atletaId);
	}
}


