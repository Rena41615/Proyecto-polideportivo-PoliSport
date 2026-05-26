package com.polisport.staff.service;

import com.polisport.staff.model.MiembrosPermisosStaff;
import com.polisport.staff.repository.MiembrosPermisosStaffRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MiembrosPermisosStaffService {

	@Autowired
	private MiembrosPermisosStaffRepository miembrosPermisosStaffRepository;

	public List<MiembrosPermisosStaff> obtenerTodos() {
		log.info("Consultando la lista completa de permisos del staff");
		return miembrosPermisosStaffRepository.findAll();
	}

	public Optional<MiembrosPermisosStaff> obtenerPorId(Long id) {
		log.info("Buscando permiso del staff con ID: {}", id);
		return miembrosPermisosStaffRepository.findById(id);
	}

	public MiembrosPermisosStaff guardarMiembrosPermisosStaff(MiembrosPermisosStaff miembrosPermisosStaff) {
		log.info("Registrando o actualizando permiso del staff");
		return miembrosPermisosStaffRepository.save(miembrosPermisosStaff);
	}

	public void eliminarMiembrosPermisosStaff(Long id) {
		log.warn("Eliminando de la base de datos el permiso del staff con ID: {}", id);
		miembrosPermisosStaffRepository.deleteById(id);
	}
}

