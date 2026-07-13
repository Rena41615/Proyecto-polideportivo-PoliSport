package com.polisport.nutricion.service;

import com.polisport.nutricion.model.PautaAlimentaria;
import com.polisport.nutricion.repository.PautaAlimentariaRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion de las pautas alimentarias.
 * <p>
 * Una pauta alimentaria representa una recomendacion de comida asociada a una
 * categoria y a un dia de la semana dentro de un {@code PlanNutricional}.
 * Esta clase actua como capa intermedia entre los controladores y el
 * {@link PautaAlimentariaRepository}, delegando en el en las operaciones CRUD.
 */
@Service
@Slf4j
public class PautaAlimentariaService {

	@Autowired
	private PautaAlimentariaRepository pautaAlimentariaRepository;

	/**
	 * Obtiene la lista completa de pautas alimentarias registradas.
	 *
	 * @return lista con todas las pautas alimentarias existentes; puede estar vacia
	 */
	public List<PautaAlimentaria> obtenerTodos() {
		log.info("Consultando la lista completa de pautas alimentarias");
		return pautaAlimentariaRepository.findAll();
	}

	/**
	 * Busca una pauta alimentaria por su identificador.
	 *
	 * @param id identificador unico de la pauta alimentaria
	 * @return un {@link Optional} con la pauta encontrada, o vacio si no existe
	 */
	public Optional<PautaAlimentaria> obtenerPorId(Long id) {
		log.info("Buscando pauta alimentaria con ID: {}", id);
		return pautaAlimentariaRepository.findById(id);
	}

	/**
	 * Crea o actualiza una pauta alimentaria. Si la entidad ya posee un ID
	 * existente, se actualiza el registro; en caso contrario se crea uno nuevo.
	 *
	 * @param pautaAlimentaria entidad a persistir
	 * @return la entidad persistida, con su ID generado si se trataba de un alta
	 */
	public PautaAlimentaria guardarPautaAlimentaria(PautaAlimentaria pautaAlimentaria) {
		log.info("Registrando o actualizando pauta alimentaria");
		return pautaAlimentariaRepository.save(pautaAlimentaria);
	}

	/**
	 * Elimina una pauta alimentaria por su identificador.
	 *
	 * @param id identificador unico de la pauta alimentaria a eliminar
	 */
	public void eliminarPautaAlimentaria(Long id) {
		log.warn("Eliminando de la base de datos la pauta alimentaria con ID: {}", id);
		pautaAlimentariaRepository.deleteById(id);
	}
}

