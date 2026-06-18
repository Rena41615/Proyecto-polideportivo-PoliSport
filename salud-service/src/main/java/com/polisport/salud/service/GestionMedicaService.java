package com.polisport.salud.service;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.repository.GestionMedicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GestionMedicaService {

    @Autowired
    private GestionMedicaRepository gestionMedicaRepository;

    public List<GestionMedica> listar() {
        log.info("Obteniendo la lista de todos los registros medicos");
        return gestionMedicaRepository.findAll();
    }

    public Optional<GestionMedica> buscarPorId(Long id) {
        log.info("Buscando registro medico con ID: {}", id);
        return gestionMedicaRepository.findById(id);
    }

    public GestionMedica crear(GestionMedica gestion) {
        log.info("Creando nuevo registro medico");
        return gestionMedicaRepository.save(gestion);
    }

    public void eliminar(Long id) {
        log.warn("Eliminando registro medico con ID: {}", id);
        gestionMedicaRepository.deleteById(id);
    }

    public GestionMedica actualizar(GestionMedica gestion) {
        log.info("Actualizando registro medico con ID: {}", gestion.getId());
        return gestionMedicaRepository.save(gestion);
    }

    public List<GestionMedica> buscarPorAtletaId(Long atletaId) {
        log.info("Buscando registros medicos para el atleta ID: {}", atletaId);
        return gestionMedicaRepository.findByAtletaId(atletaId);
    }
}