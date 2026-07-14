package com.polisport.staff.service;

import com.polisport.staff.model.MiembrosRolStaff;
import com.polisport.staff.repository.MiembrosRolStaffRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion CRUD de la asignacion de
 * roles internos a los miembros del staff del polideportivo.
 * <p>
 * Actua como capa intermedia entre los controladores y
 * {@link MiembrosRolStaffRepository}, delegando la persistencia en
 * Spring Data JPA sin aplicar reglas de negocio adicionales.
 */
@Service
@Slf4j
public class MiembrosRolStaffService {

	@Autowired
	private MiembrosRolStaffRepository miembrosRolStaffRepository;

	/**
	 * Obtiene todas las asignaciones de rol de staff registradas en el sistema.
	 *
	 * @return lista completa de {@link MiembrosRolStaff}; puede estar vacia
	 */
	public List<MiembrosRolStaff> obtenerTodos() {
		log.info("Consultando la lista completa de roles del staff");
		return miembrosRolStaffRepository.findAll();
	}

	/**
	 * Busca una asignacion de rol de staff por su identificador.
	 *
	 * @param id identificador de la asignacion a buscar
	 * @return {@link Optional} con la asignacion encontrada, o vacio si no existe
	 */
	public Optional<MiembrosRolStaff> obtenerPorId(Long id) {
		log.info("Buscando rol del staff con ID: {}", id);
		return miembrosRolStaffRepository.findById(id);
	}

	/**
	 * Crea o actualiza una asignacion de rol de staff. Si la entidad ya
	 * posee un identificador existente, se actualiza el registro
	 * correspondiente; en caso contrario, se crea uno nuevo.
	 *
	 * @param miembrosRolStaff entidad de asignacion de rol a guardar o actualizar
	 * @return la entidad persistida, con su identificador asignado
	 */
	public MiembrosRolStaff guardarMiembrosRolStaff(MiembrosRolStaff miembrosRolStaff) {
		log.info("Registrando o actualizando rol del staff");
		return miembrosRolStaffRepository.save(miembrosRolStaff);
	}

	/**
	 * Elimina una asignacion de rol de staff por su identificador.
	 *
	 * @param id identificador de la asignacion a eliminar
	 * @throws org.springframework.dao.EmptyResultDataAccessException si no existe
	 *         una asignacion con el ID indicado (propagada por el repositorio)
	 */
	public void eliminarMiembrosRolStaff(Long id) {
		log.warn("Eliminando de la base de datos el rol del staff con ID: {}", id);
		miembrosRolStaffRepository.deleteById(id);
	}
}

