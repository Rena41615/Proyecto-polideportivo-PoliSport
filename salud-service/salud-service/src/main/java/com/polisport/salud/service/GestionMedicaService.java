package com.polisport.salud.service;
import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.repository.GestionMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionMedicaService {

    @Autowired
    private GestionMedicaRepository gestionMedicaRepository;

    // LISTAR TODOS
    public List<GestionMedica> listar() {
        return gestionMedicaRepository.findAll();
    }

    // BUSCAR POR ID
    public Optional<GestionMedica> buscarPorId(Long id) {
        return gestionMedicaRepository.findById(id);
    }

    // CREAR
    public GestionMedica crear(GestionMedica gestion) {
        return gestionMedicaRepository.save(gestion);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        gestionMedicaRepository.deleteById(id);
    }

    //Actualizar
    public GestionMedica actualizar(GestionMedica gestion) {
        return gestionMedicaRepository.save(gestion);
    }
}