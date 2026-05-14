package com.polisport.biometria.service;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.repository.AnalisisBiometricoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AnalisisBiometricoService {

    @Autowired
    private AnalisisBiometricoRepository analisisBiometricoRepository;

    public List<AnalisisBiometrico> listar() {
        log.info("Obteniendo la lista de todos los análisis biométricos");
        return analisisBiometricoRepository.findAll();
    }

    public Optional<AnalisisBiometrico> buscarPorId(Long id) {
        log.info("Buscando análisis biométrico con ID: {}", id);
        return analisisBiometricoRepository.findById(id);
    }

    public AnalisisBiometrico crear(AnalisisBiometrico analisis) {
        log.info("Creando nuevo análisis biométrico para el atleta ID: {}", analisis.getAtletaId());
        return analisisBiometricoRepository.save(analisis);
    }

    public void eliminar(Long id) {
        log.warn("Eliminando análisis biométrico con ID: {}", id);
        analisisBiometricoRepository.deleteById(id);
    }

    public AnalisisBiometrico actualizar(AnalisisBiometrico analisis) {
        log.info("Actualizando análisis biométrico con ID: {}", analisis.getId());
        return analisisBiometricoRepository.save(analisis);
    }
}