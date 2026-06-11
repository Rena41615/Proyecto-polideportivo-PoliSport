package com.polisport.biometria.controller;

import com.polisport.biometria.dto.AnalisisBiometricoCrearDTO;
import com.polisport.biometria.dto.AnalisisBiometricoDTO;
import com.polisport.biometria.mapper.AnalisisBiometricoMapper;
import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.service.AnalisisBiometricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/biometria")
@CrossOrigin(origins = "*")
@Tag(name = "Análisis Biométrico", description = "Operaciones CRUD para análisis biométricos")
public class AnalisisBiometricoController {

    private final AnalisisBiometricoService analisisBiometricoService;
    private final AnalisisBiometricoMapper mapper;

    public AnalisisBiometricoController(AnalisisBiometricoService analisisBiometricoService, AnalisisBiometricoMapper mapper) {
        this.analisisBiometricoService = analisisBiometricoService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Listar todos los análisis biométricos", description = "Obtiene la lista completa de todos los análisis biométricos registrados en el sistema")
    public ResponseEntity<List<AnalisisBiometricoDTO>> listar() {
        log.info("Peticion REST recibida para listar analisis biometricos");
        List<AnalisisBiometrico> analisis = analisisBiometricoService.listar();
        List<AnalisisBiometricoDTO> dtos = analisis.stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar análisis biométrico por ID", description = "Obtiene los detalles de un análisis biométrico específico usando su identificador único")
    public ResponseEntity<AnalisisBiometricoDTO> buscarPorId(@PathVariable Long id) {
        log.info("Peticion REST recibida para buscar analisis con ID: {}", id);
        return analisisBiometricoService.buscarPorId(id)
                .map(mapper::entityToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.error("Analisis biometrico con ID {} no encontrado", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis biométrico", description = "Crea un nuevo registro de análisis biométrico con la información proporcionada")
    public ResponseEntity<AnalisisBiometricoDTO> crear(@Validated @RequestBody AnalisisBiometricoCrearDTO dto) {
        log.info("Peticion REST recibida para crear un analisis biometrico");
        AnalisisBiometrico analisis = mapper.crearDTOToEntity(dto);
        AnalisisBiometrico creado = analisisBiometricoService.crear(analisis);
        AnalisisBiometricoDTO respuesta = mapper.entityToDTO(creado);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis biométrico existente", description = "Actualiza los datos de un análisis biométrico específico identificado por su ID")
    public ResponseEntity<AnalisisBiometricoDTO> actualizar(@PathVariable Long id, @Validated @RequestBody AnalisisBiometricoCrearDTO dto) {
        log.info("Peticion REST recibida para actualizar analisis con ID: {}", id);
        AnalisisBiometrico analisis = mapper.crearDTOToEntity(dto);
        analisis.setId(id);
        AnalisisBiometrico actualizado = analisisBiometricoService.actualizar(analisis);
        AnalisisBiometricoDTO respuesta = mapper.entityToDTO(actualizado);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis biométrico", description = "Elimina un análisis biométrico específico del sistema identificado por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Peticion REST recibida para eliminar analisis con ID: {}", id);
        analisisBiometricoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
