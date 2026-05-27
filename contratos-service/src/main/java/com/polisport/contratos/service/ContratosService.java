package com.polisport.contratos.service;

import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.repository.ContratosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContratosService {

    @Autowired
    private ContratosRepository contratosRepository;

    public List<Contrato> obtenerTodos() {
        log.info("Consultando todos los contratos registrados");
        return contratosRepository.findAll();
    }

    public Optional<Contrato> obtenerPorId(Long id) {
        log.info("Buscando contrato con ID: {}", id);
        return contratosRepository.findById(id);
    }

    public List<Contrato> listarPorEmpleado(Integer run) {
        log.info("Buscando historial de contratos para el empleado RUN: {}", run);
        return contratosRepository.findByRunEmpleado(run);
    }

    public Contrato guardar(Contrato contrato) {
        log.info("Guardando contrato para el empleado RUN: {}", contrato.getRunEmpleado());
        return contratosRepository.save(contrato);
    }

    public void eliminar(Long id) {
        log.warn("Eliminando contrato con ID: {}", id);
        contratosRepository.deleteById(id);
    }
}