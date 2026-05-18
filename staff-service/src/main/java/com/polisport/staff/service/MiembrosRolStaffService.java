package com.polisport.staff.service;

import com.polisport.staff.model.MiembrosRolStaff;
import com.polisport.staff.repository.MiembrosRolStaffRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MiembrosRolStaffService {

	@Autowired
	private MiembrosRolStaffRepository miembrosRolStaffRepository;

	public List<MiembrosRolStaff> obtenerTodos() {
		log.info("Consultando la lista completa de roles del staff");
		return miembrosRolStaffRepository.findAll();
	}

	public Optional<MiembrosRolStaff> obtenerPorId(Long id) {
		log.info("Buscando rol del staff con ID: {}", id);
		return miembrosRolStaffRepository.findById(id);
	}

	public MiembrosRolStaff guardarMiembrosRolStaff(MiembrosRolStaff miembrosRolStaff) {
		log.info("Registrando o actualizando rol del staff");
		return miembrosRolStaffRepository.save(miembrosRolStaff);
	}

	public void eliminarMiembrosRolStaff(Long id) {
		log.warn("Eliminando de la base de datos el rol del staff con ID: {}", id);
		miembrosRolStaffRepository.deleteById(id);
	}
}

