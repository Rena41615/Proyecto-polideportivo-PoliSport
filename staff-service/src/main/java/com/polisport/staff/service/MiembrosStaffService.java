package com.polisport.staff.service;

import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.repository.MiembrosStaffRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MiembrosStaffService {

	@Autowired
	private MiembrosStaffRepository miembrosStaffRepository;

	public List<MiembrosStaff> obtenerTodos() {
		log.info("Consultando la lista completa de miembros del staff");
		return miembrosStaffRepository.findAll();
	}

	public Optional<MiembrosStaff> obtenerPorId(Long id) {
		log.info("Buscando miembro del staff con ID: {}", id);
		return miembrosStaffRepository.findById(id);
	}

	public MiembrosStaff guardarMiembrosStaff(MiembrosStaff miembrosStaff) {
		log.info("Registrando o actualizando miembro del staff: {}", miembrosStaff.getDocumento());
		return miembrosStaffRepository.save(miembrosStaff);
	}

	public void eliminarMiembrosStaff(Long id) {
		log.warn("Eliminando de la base de datos el miembro del staff con ID: {}", id);
		miembrosStaffRepository.deleteById(id);
	}
}

