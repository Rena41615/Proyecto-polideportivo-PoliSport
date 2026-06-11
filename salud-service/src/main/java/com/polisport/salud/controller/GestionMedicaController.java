package com.polisport.salud.controller;

import com.polisport.salud.dto.GestionMedicaDTO;
import com.polisport.salud.dto.GestionMedicaCrearDTO;
import com.polisport.salud.mapper.GestionMedicaMapper;
import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.service.GestionMedicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Gestión Médica", description = "Operaciones CRUD para registros médicos")
public class GestionMedicaController {

    private final GestionMedicaService gestionMedicaService;
    private final GestionMedicaMapper gestionMedicaMapper;

    public GestionMedicaController(GestionMedicaService gestionMedicaService, GestionMedicaMapper gestionMedicaMapper) {
        this.gestionMedicaService = gestionMedicaService;
        this.gestionMedicaMapper = gestionMedicaMapper;
    }

    @Operation(summary = "Listar todas las gestiones médicas", description = "Obtiene el listado completo de todos los registros médicos almacenados")
    @GetMapping
    public ResponseEntity<List<GestionMedicaDTO>> listar() {
        log.info("Peticion REST recibida para listar gestiones medicas");
        List<GestionMedica> gestiones = gestionMedicaService.listar();
        List<GestionMedicaDTO> dtos = gestiones.stream()
                .map(gestionMedicaMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Buscar gestion médica por ID", description = "Obtiene los detalles de una gestion médica específica usando su identificador único")
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

    @Operation(summary = "Crear nuevo registro médico", description = "Crea e inserta un nuevo registro médico en el sistema con los datos proporcionados")
    @PostMapping
    public ResponseEntity<GestionMedicaDTO> crear(@Valid @RequestBody GestionMedicaCrearDTO crearDTO) {
        log.info("Peticion REST recibida para crear un registro medico");
        GestionMedica gestionMedica = gestionMedicaMapper.crearDTOToEntity(crearDTO);
        GestionMedica creada = gestionMedicaService.crear(gestionMedica);
        return new ResponseEntity<>(gestionMedicaMapper.entityToDTO(creada), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar registro médico", description = "Actualiza los datos de un registro médico existente usando su identificador único")
    @PutMapping("/{id}")
    public ResponseEntity<GestionMedicaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody GestionMedicaCrearDTO crearDTO) {
        log.info("Peticion REST recibida para actualizar registro medico con ID: {}", id);
        GestionMedica gestionMedica = gestionMedicaMapper.crearDTOToEntity(crearDTO);
        gestionMedica.setId(id);
        GestionMedica actualizada = gestionMedicaService.actualizar(gestionMedica);
        return ResponseEntity.ok(gestionMedicaMapper.entityToDTO(actualizada));
    }

    @Operation(summary = "Eliminar registro médico", description = "Elimina un registro médico del sistema usando su identificador único")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Peticion REST recibida para eliminar registro medico con ID: {}", id);
        gestionMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}