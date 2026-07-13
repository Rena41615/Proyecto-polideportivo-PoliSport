package com.polisport.nutricion.service;

import com.polisport.nutricion.model.RestriccionAlimentaria;
import com.polisport.nutricion.repository.RestriccionAlimentariaRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion de las restricciones alimentarias.
 * <p>
 * Una restriccion alimentaria representa una limitacion o alergia (por ejemplo,
 * intolerancia al gluten o a la lactosa) asociada a un {@code PlanNutricional}
 * de un atleta. Esta clase delega las operaciones CRUD en el
 * {@link RestriccionAlimentariaRepository}.
 */
@Service
@Slf4j
public class RestriccionAlimentariaService {

	@Autowired
	private RestriccionAlimentariaRepository restriccionAlimentariaRepository;

	/**
	 * Obtiene la lista completa de restricciones alimentarias registradas.
	 *
	 * @return lista con todas las restricciones alimentarias existentes; puede estar vacia
	 */
	public List<RestriccionAlimentaria> obtenerTodos() {
		log.info("Consultando la lista completa de restricciones alimentarias");
		return restriccionAlimentariaRepository.findAll();
	}

	/**
	 * Busca una restriccion alimentaria por su identificador.
	 *
	 * @param id identificador unico de la restriccion alimentaria
	 * @return un {@link Optional} con la restriccion encontrada, o vacio si no existe
	 */
	public Optional<RestriccionAlimentaria> obtenerPorId(Long id) {
		log.info("Buscando restriccion alimentaria con ID: {}", id);
		return restriccionAlimentariaRepository.findById(id);
	}

	/**
	 * Crea o actualiza una restriccion alimentaria. Si la entidad ya posee un ID
	 * existente, se actualiza el registro; en caso contrario se crea uno nuevo.
	 *
	 * @param restriccionAlimentaria entidad a persistir
	 * @return la entidad persistida, con su ID generado si se trataba de un alta
	 */
	public RestriccionAlimentaria guardarRestriccionAlimentaria(RestriccionAlimentaria restriccionAlimentaria) {
		log.info("Registrando o actualizando restriccion alimentaria");
		return restriccionAlimentariaRepository.save(restriccionAlimentaria);
	}

	/**
	 * Elimina una restriccion alimentaria por su identificador.
	 *
	 * @param id identificador unico de la restriccion alimentaria a eliminar
	 */
	public void eliminarRestriccionAlimentaria(Long id) {
		log.warn("Eliminando de la base de datos la restriccion alimentaria con ID: {}", id);
		restriccionAlimentariaRepository.deleteById(id);
	}
}

