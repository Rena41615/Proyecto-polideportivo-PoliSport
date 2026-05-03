package com.polisport.biometria.service;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.repository.AnalisisBiometricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnalisisBiometricoService {
    @Autowired
    private AnalisisBiometricoRepository analisisBiometricoRepository;
    // LISTAR TODOS
    public List<AnalisisBiometrico> listar() {
        return analisisBiometricoRepository.findAll();
    }

    // BUSCAR POR ID
    public Optional<AnalisisBiometrico> buscarPorId(Long id) {
        return analisisBiometricoRepository.findById(id);
    }

    // CREAR
    public AnalisisBiometrico crear(AnalisisBiometrico analisis) {
        return analisisBiometricoRepository.save(analisis);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        analisisBiometricoRepository.deleteById(id);
    }
    //Actualizar
    public AnalisisBiometrico actualizar(AnalisisBiometrico analisis) {
        return analisisBiometricoRepository.save(analisis);
    }
}
