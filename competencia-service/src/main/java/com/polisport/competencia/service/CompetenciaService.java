package com.polisport.competencia.service;

import com.polisport.competencia.model.Competencia;
import com.polisport.competencia.repository.CompetenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Transactional(readOnly = true)
    public List<Competencia> obtenerTodas(){
        log.info("Consultando listado de todas las competencias");
        return competenciaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Competencia> obtenerPorId(Long id){
        log.info("Buscando competencia con ID: {}", id);
        return competenciaRepository.findById(id);
    }

    public Competencia guardar(Competencia competencia){
        log.info("Registrando competencia: {}", competencia.getNombreCompetencia());
        return competenciaRepository.save(competencia);
    }

    public void eliminar(Long id){
        log.warn("Eliminando competencia con ID: {}", id);
        competenciaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Competencia> listarPorAtleta(Integer run) {
        log.info("Buscando competencias para el atleta con run: {}", run);
        return competenciaRepository.buscarPorAtletaInscrito(run);
    }

}