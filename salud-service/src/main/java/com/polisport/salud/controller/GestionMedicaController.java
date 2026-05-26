package com.polisport.salud.controller;

import com.polisport.common.dto.salud.GestionMedicaDTO;
import com.polisport.common.dto.salud.GestionMedicaCrearDTO;
import com.polisport.common.mapper.salud.GestionMedicaMapper;
import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.service.GestionMedicaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/salud")
@CrossOrigin(origins = "*")
public class GestionMedicaController {

    private final GestionMedicaService gestionMedicaService;
    private final GestionMedicaMapper gestionMedicaMapper;

    public GestionMedicaController(GestionMedicaService gestionMedicaService, GestionMedicaMapper gestionMedicaMapper) {
        this.gestionMedicaService = gestionMedicaService;
        this.gestionMedicaMapper = gestionMedicaMapper;
    }

    @GetMapping
    public ResponseEntity<List<GestionMedicaDTO>> listar() {
        log.info("Peticion REST recibida para listar gestiones medicas");
        List<GestionMedica> gestiones = gestionMedicaService.listar();
        List<GestionMedicaDTO> dtos = gestiones.stream()
                .map(gestionMedicaMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestionMedicaDTO> buscarPorId(@PathVariable Long id) {
        log.info("Peticion REST recibida para buscar gestion medica con ID: {}", id);
        return gestionMedicaService.buscarPorId(id)
                .map(gestion -> ResponseEntity.ok(gestionMedicaMapper.entityToDTO(gestion)))
                .orElseGet(() -> {
                    log.error("Gestion medica con ID {} no encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<GestionMedicaDTO> crear(@Valid @RequestBody GestionMedicaCrearDTO crearDTO) {
        log.info("Peticion REST recibida para crear un registro medico");
        GestionMedica gestionMedica = gestionMedicaMapper.crearDTOToEntity(crearDTO);
        GestionMedica creada = gestionMedicaService.crear(gestionMedica);
        return new ResponseEntity<>(gestionMedicaMapper.entityToDTO(creada), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestionMedicaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody GestionMedicaCrearDTO crearDTO) {
        log.info("Peticion REST recibida para actualizar registro medico con ID: {}", id);
        GestionMedica gestionMedica = gestionMedicaMapper.crearDTOToEntity(crearDTO);
        gestionMedica.setId(id);
        GestionMedica actualizada = gestionMedicaService.actualizar(gestionMedica);
        return ResponseEntity.ok(gestionMedicaMapper.entityToDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Peticion REST recibida para eliminar registro medico con ID: {}", id);
        gestionMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}