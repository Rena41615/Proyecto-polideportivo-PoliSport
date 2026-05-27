package com.polisport.atletas.service;

import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.repository.AtletaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j //jerarquia de mensajes

public class AtletaService {

    @Autowired //permite comunicacion con el repository
    private AtletaRepository atletaRepository;

    public List<Atleta> obtenerTodos() {
        log.info("Consultando la lista completa de atletas");
        return atletaRepository.findAll();
    }

    public Optional<Atleta> obtenerPorId(Long id){
        log.info("Buscando atleta con ID: {}", id);
        return atletaRepository.findById(id);
    }

    public Atleta guardarAtleta(Atleta atleta){
        log.info("Registrando o actualizando atleta con RUT: {}", atleta.getRunAtleta());
        return atletaRepository.save(atleta);
    }

    public void eliminarAtleta(Long id){
        log.warn("Eliminando de la base de datos al atleta con ID: {}", id);
        atletaRepository.deleteById(id);
    }

    public Optional<Atleta> obtenerPorRun(Integer run) {
        return atletaRepository.findByRunAtleta(run);
    }

}