package com.polisport.inventario.service;

import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository instalacionRepository;

    public List<Instalacion> listar() {
        return instalacionRepository.findAll();
    }

    public Optional<Instalacion> buscarPorId(Long id) {
        return instalacionRepository.findById(id);
    }

    public Instalacion crear(Instalacion instalacion) {
        return instalacionRepository.save(instalacion);
    }

    public Instalacion actualizar(Instalacion instalacion) {
        return instalacionRepository.save(instalacion);
    }

    public void eliminar(Long id) {
        instalacionRepository.deleteById(id);
    }
}