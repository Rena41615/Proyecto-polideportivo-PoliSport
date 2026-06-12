package com.polisport.atletas.controller;

import com.polisport.atletas.dto.AtletaCrearDTO;
import com.polisport.atletas.dto.AtletaDTO;
import com.polisport.atletas.mapper.AtletaMapper;
import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.service.AtletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AtletaController: Controlador REST para gestión de atletas.
 * Utiliza DTOs para desacoplar la API del modelo JPA.
 */
@Slf4j
@RestController
@RequestMapping("/api/atletas")
@CrossOrigin(origins = "*")
@Tag(name = "Atleta", description = "Operaciones CRUD para atletas")
public class AtletaController {

    private final AtletaService atletaService;
    private final AtletaMapper atletaMapper;

    public AtletaController(AtletaService atletaService, AtletaMapper atletaMapper) {
        this.atletaService = atletaService;
        this.atletaMapper = atletaMapper;
    }

    /**
     * Obtener todos los atletas.
     * GET /api/atletas
     */
    @GetMapping
    @Operation(summary = "Listar todos los atletas", description = "Obtiene la lista completa de todos los atletas registrados en el sistema")
    public ResponseEntity<List<AtletaDTO>> listarTodos() {
        log.info("Consultando lista completa de atletas");
        List<Atleta> atletas = atletaService.obtenerTodos();
        List<AtletaDTO> dtos = atletas.stream()
                .map(atletaMapper::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener atleta por ID.
     * GET /api/atletas/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar atleta por ID", description = "Obtiene los detalles de un atleta específico usando su identificador único")
    public ResponseEntity<AtletaDTO> obtenerPorId(@PathVariable Long id) {
        log.info("Buscando atleta con ID: {}", id);
        return atletaService.obtenerPorId(id)
                .map(atletaMapper::entityToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Atleta con ID {} no encontrado", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Obtener atleta por RUN.
     * GET /api/atletas/run/{run}
     */
    @GetMapping("/run/{run}")
    @Operation(summary = "Buscar atleta por RUN", description = "Obtiene los detalles de un atleta usando su número de RUN (Rol Único Nacional)")
    public ResponseEntity<AtletaDTO> obtenerPorRun(@PathVariable Integer run) {
        log.info("Buscando atleta con RUN: {}", run);
        return atletaService.obtenerPorRun(run)
                .map(atletaMapper::entityToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Atleta con RUN {} no encontrado", run);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Crear nuevo atleta.
     * POST /api/atletas
     */
    @PostMapping
    @Operation(summary = "Crear nuevo atleta", description = "Crea un nuevo registro de atleta con la información proporcionada")
    public ResponseEntity<AtletaDTO> crear(@Valid @RequestBody AtletaCrearDTO dto) {
        log.info("Creando nuevo atleta con RUN: {}", dto.getRunAtleta());
        Atleta atleta = atletaMapper.crearDTOToEntity(dto);
        Atleta nuevoAtleta = atletaService.guardarAtleta(atleta);
        AtletaDTO respuesta = atletaMapper.entityToDTO(nuevoAtleta);
        log.info("Atleta creado exitosamente con ID: {}", nuevoAtleta.getId());
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Actualizar atleta existente.
     * PUT /api/atletas/{id}
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar atleta existente", description = "Actualiza los datos de un atleta específico identificado por su ID")
    public ResponseEntity<AtletaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AtletaCrearDTO dto) {
        log.info("Actualizando atleta con ID: {}", id);
        Atleta atleta = atletaMapper.crearDTOToEntity(dto);
        atleta.setId(id);
        Atleta actualizado = atletaService.guardarAtleta(atleta);
        AtletaDTO respuesta = atletaMapper.entityToDTO(actualizado);
        log.info("Atleta actualizado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    /**
     * Actualización parcial.
     * PATCH /api/atletas/{id}
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente", description = "Actualiza solo campos específicos de un atleta")
    public ResponseEntity<AtletaDTO> actualizarParcial(@PathVariable Long id, @RequestBody java.util.Map<String, Object> updates) {
        log.info("Actualización parcial recibida para ID: {}", id);
        Atleta atletaActualizado = atletaService.actualizarParcial(id, updates);
        return ResponseEntity.ok(atletaMapper.entityToDTO(atletaActualizado));
    }

    /**
     * Eliminar atleta.
     * DELETE /api/atletas/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar atleta", description = "Elimina un atleta específico del sistema identificado por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Eliminando atleta con ID: {}", id);
        atletaService.eliminarAtleta(id);
        log.info("Atleta eliminado exitosamente");
        return ResponseEntity.noContent().build();
    }
}