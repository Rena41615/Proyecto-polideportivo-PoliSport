package com.polisport.staff.service;

import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.repository.MiembrosStaffRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion CRUD de los miembros del
 * staff del polideportivo (entrenadores, administrativos, etc.).
 * <p>
 * Actua como capa intermedia entre los controladores y
 * {@link MiembrosStaffRepository}, delegando la persistencia en Spring
 * Data JPA sin aplicar reglas de negocio adicionales.
 */
@Service
@Slf4j
public class MiembrosStaffService {

	@Autowired
	private MiembrosStaffRepository miembrosStaffRepository;

	/**
	 * Obtiene todos los miembros del staff registrados en el sistema.
	 *
	 * @return lista completa de {@link MiembrosStaff}; puede estar vacia
	 */
	public List<MiembrosStaff> obtenerTodos() {
		log.info("Consultando la lista completa de miembros del staff");
		return miembrosStaffRepository.findAll();
	}

	/**
	 * Busca un miembro del staff por su identificador.
	 *
	 * @param id identificador del miembro a buscar
	 * @return {@link Optional} con el miembro encontrado, o vacio si no existe
	 */
	public Optional<MiembrosStaff> obtenerPorId(Long id) {
		log.info("Buscando miembro del staff con ID: {}", id);
		return miembrosStaffRepository.findById(id);
	}

	/**
	 * Crea o actualiza un miembro del staff. Si la entidad ya posee un
	 * identificador existente, se actualiza el registro correspondiente;
	 * en caso contrario, se crea uno nuevo.
	 *
	 * @param miembrosStaff entidad de miembro del staff a guardar o actualizar
	 * @return la entidad persistida, con su identificador asignado
	 */
	public MiembrosStaff guardarMiembrosStaff(MiembrosStaff miembrosStaff) {
		// Se registra el documento (identificador de negocio) para trazabilidad
		log.info("Registrando o actualizando miembro del staff: {}", miembrosStaff.getDocumento());
		return miembrosStaffRepository.save(miembrosStaff);
	}

	/**
	 * Elimina un miembro del staff por su identificador.
	 *
	 * @param id identificador del miembro a eliminar
	 * @throws org.springframework.dao.EmptyResultDataAccessException si no existe
	 *         un miembro con el ID indicado (propagada por el repositorio)
	 */
	public void eliminarMiembrosStaff(Long id) {
		log.warn("Eliminando de la base de datos el miembro del staff con ID: {}", id);
		miembrosStaffRepository.deleteById(id);
	}
}

