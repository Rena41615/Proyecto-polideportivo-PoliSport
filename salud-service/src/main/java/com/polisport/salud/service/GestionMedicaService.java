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
        log.info("Obteniendo la lista de todos los registros médicos");
        return gestionMedicaRepository.findAll();
    }

    public Optional<GestionMedica> buscarPorId(Long id) {
        log.info("Buscando registro médico con ID: {}", id);
        return gestionMedicaRepository.findById(id);
    }

    public GestionMedica crear(GestionMedica gestion) {
        log.info("Creando nuevo registro médico");
        return gestionMedicaRepository.save(gestion);
    }

    public void eliminar(Long id) {
        log.warn("Eliminando registro médico con ID: {}", id);
        gestionMedicaRepository.deleteById(id);
    }

    public GestionMedica actualizar(GestionMedica gestion) {
        log.info("Actualizando registro médico con ID: {}", gestion.getId());
        return gestionMedicaRepository.save(gestion);
    }
}