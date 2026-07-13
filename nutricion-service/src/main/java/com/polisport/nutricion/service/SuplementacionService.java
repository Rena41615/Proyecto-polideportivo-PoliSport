package com.polisport.nutricion.service;

import com.polisport.nutricion.model.Suplementacion;
import com.polisport.nutricion.repository.SuplementacionRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion de las suplementaciones.
 * <p>
 * Una suplementacion representa un suplemento nutricional (por ejemplo,
 * proteina o creatina) con su dosis y horario de consumo, asociado a un
 * {@code PlanNutricional} de un atleta. Esta clase delega las operaciones
 * CRUD en el {@link SuplementacionRepository}.
 */
@Service
@Slf4j
public class SuplementacionService {

	@Autowired
	private SuplementacionRepository suplementacionRepository;

	/**
	 * Obtiene la lista completa de suplementaciones registradas.
	 *
	 * @return lista con todas las suplementaciones existentes; puede estar vacia
	 */
	public List<Suplementacion> obtenerTodos() {
		log.info("Consultando la lista completa de suplementaciones");
		return suplementacionRepository.findAll();
	}

	/**
	 * Busca una suplementacion por su identificador.
	 *
	 * @param id identificador unico de la suplementacion
	 * @return un {@link Optional} con la suplementacion encontrada, o vacio si no existe
	 */
	public Optional<Suplementacion> obtenerPorId(Long id) {
		log.info("Buscando suplementacion con ID: {}", id);
		return suplementacionRepository.findById(id);
	}

	/**
	 * Crea o actualiza una suplementacion. Si la entidad ya posee un ID
	 * existente, se actualiza el registro; en caso contrario se crea uno nuevo.
	 *
	 * @param suplementacion entidad a persistir
	 * @return la entidad persistida, con su ID generado si se trataba de un alta
	 */
	public Suplementacion guardarSuplementacion(Suplementacion suplementacion) {
		log.info("Registrando o actualizando suplementacion");
		return suplementacionRepository.save(suplementacion);
	}

	/**
	 * Elimina una suplementacion por su identificador.
	 *
	 * @param id identificador unico de la suplementacion a eliminar
	 */
	public void eliminarSuplementacion(Long id) {
		log.warn("Eliminando de la base de datos la suplementacion con ID: {}", id);
		suplementacionRepository.deleteById(id);
	}
}

