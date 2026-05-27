package com.polisport.inventario.service;

import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario crear(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario actualizar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }
}