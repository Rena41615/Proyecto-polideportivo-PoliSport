package com.polisport.entrenamiento.service;

import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.entrenamiento.repository.EntrenamientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    public List<Entrenamiento> obtenerTodos(){
        log.info("Consultando todos los entrenamientos registros");
        return entrenamientoRepository.findAll();
    }

    public Optional<Entrenamiento> obtenerPorId(Long id){
        log.info("Buscando entrenamiento con ID: {}", id);
        return entrenamientoRepository.findById(id);
    }

    public Entrenamiento guardar(Entrenamiento entrenamiento){
        log.info("Registrando sesion de entrenamiento para el entrenador RUN: {}", entrenamiento.getRunEntrenador());
        return entrenamientoRepository.save(entrenamiento);
    }

    public void eliminar(Long id){
        log.warn("Eliminando entrenamiento con ID: {}", id);
        entrenamientoRepository.deleteById(id);
    }

    public List<Entrenamiento> listarPorAtleta(Integer run){
        log.info("Obteniendo historial de entrenamientos para el atleta RUN: {}", run);
        return entrenamientoRepository.buscarPorAtleta(run);
    }

}