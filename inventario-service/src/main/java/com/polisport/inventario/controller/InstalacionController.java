package com.polisport.inventario.controller;

import com.polisport.inventario.dto.InstalacionDTO;
import com.polisport.inventario.dto.InstalacionCrearDTO;
import com.polisport.inventario.mapper.InstalacionMapper;
import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.service.InstalacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instalaciones")
@CrossOrigin(origins = "*")
public class InstalacionController {

    private final InstalacionService instalacionService;
    private final InstalacionMapper instalacionMapper;

    public InstalacionController(InstalacionService instalacionService, InstalacionMapper instalacionMapper) {
        this.instalacionService = instalacionService;
        this.instalacionMapper = instalacionMapper;
    }

    @GetMapping
    public ResponseEntity<List<InstalacionDTO>> listar() {
        List<Instalacion> instalaciones = instalacionService.listar();
        List<InstalacionDTO> dtos = instalaciones.stream()
                .map(instalacionMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstalacionDTO> buscarPorId(@PathVariable Long id) {
        return instalacionService.buscarPorId(id)
                .map(instalacion -> ResponseEntity.ok(instalacionMapper.entityToDTO(instalacion)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InstalacionDTO> crear(@Valid @RequestBody InstalacionCrearDTO crearDTO) {
        Instalacion instalacion = instalacionMapper.crearDTOToEntity(crearDTO);
        Instalacion creada = instalacionService.crear(instalacion);
        return new ResponseEntity<>(instalacionMapper.entityToDTO(creada), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstalacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InstalacionCrearDTO crearDTO) {
        Instalacion instalacion = instalacionMapper.crearDTOToEntity(crearDTO);
        instalacion.setId(id);
        Instalacion actualizada = instalacionService.actualizar(instalacion);
        return ResponseEntity.ok(instalacionMapper.entityToDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        instalacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}