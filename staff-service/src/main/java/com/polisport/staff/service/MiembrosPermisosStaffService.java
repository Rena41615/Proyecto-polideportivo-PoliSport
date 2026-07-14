package com.polisport.staff.service;

import com.polisport.staff.model.MiembrosPermisosStaff;
import com.polisport.staff.repository.MiembrosPermisosStaffRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio encargado de la gestion CRUD de los permisos
 * internos asignados a los miembros del staff del polideportivo.
 * <p>
 * Actua como capa intermedia entre los controladores y
 * {@link MiembrosPermisosStaffRepository}, delegando la persistencia en
 * Spring Data JPA sin aplicar reglas de negocio adicionales.
 */
@Service
@Slf4j
public class MiembrosPermisosStaffService {

	@Autowired
	private MiembrosPermisosStaffRepository miembrosPermisosStaffRepository;

	/**
	 * Obtiene todos los permisos de staff registrados en el sistema.
	 *
	 * @return lista completa de {@link MiembrosPermisosStaff}; puede estar vacia
	 */
	public List<MiembrosPermisosStaff> obtenerTodos() {
		log.info("Consultando la lista completa de permisos del staff");
		return miembrosPermisosStaffRepository.findAll();
	}

	/**
	 * Busca un permiso de staff por su identificador.
	 *
	 * @param id identificador del permiso a buscar
	 * @return {@link Optional} con el permiso encontrado, o vacio si no existe
	 */
	public Optional<MiembrosPermisosStaff> obtenerPorId(Long id) {
		log.info("Buscando permiso del staff con ID: {}", id);
		return miembrosPermisosStaffRepository.findById(id);
	}

	/**
	 * Crea o actualiza un permiso de staff. Si la entidad ya posee un
	 * identificador existente, se actualiza el registro correspondiente;
	 * en caso contrario, se crea uno nuevo.
	 *
	 * @param miembrosPermisosStaff entidad de permiso a guardar o actualizar
	 * @return la entidad persistida, con su identificador asignado
	 */
	public MiembrosPermisosStaff guardarMiembrosPermisosStaff(MiembrosPermisosStaff miembrosPermisosStaff) {
		log.info("Registrando o actualizando permiso del staff");
		return miembrosPermisosStaffRepository.save(miembrosPermisosStaff);
	}

	/**
	 * Elimina un permiso de staff por su identificador.
	 *
	 * @param id identificador del permiso a eliminar
	 * @throws org.springframework.dao.EmptyResultDataAccessException si no existe
	 *         un permiso con el ID indicado (propagada por el repositorio)
	 */
	public void eliminarMiembrosPermisosStaff(Long id) {
		log.warn("Eliminando de la base de datos el permiso del staff con ID: {}", id);
		miembrosPermisosStaffRepository.deleteById(id);
	}
}

