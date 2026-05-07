package com.polisport.biometria.controller;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.service.AnalisisBiometricoService;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/biometria")
public class AnalisisBiometricoController {

    private final AnalisisBiometricoService analisisBiometricoService;

    public AnalisisBiometricoController(AnalisisBiometricoService analisisBiometricoService) {
        this.analisisBiometricoService = analisisBiometricoService;
    }

    @GetMapping
    public ResponseEntity<List<AnalisisBiometrico>> listar() {
        log.info("Petición REST recibida para listar análisis biométricos");
        return ResponseEntity.ok(analisisBiometricoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalisisBiometrico> buscarPorId(@PathVariable Long id) {
        log.info("Petición REST recibida para buscar análisis con ID: {}", id);
        return analisisBiometricoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.error("Análisis biométrico con ID {} no encontrado", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<AnalisisBiometrico> crear(@Validated @RequestBody AnalisisBiometrico analisisBiometrico) {
        log.info("Petición REST recibida para crear un análisis biométrico");
        return ResponseEntity.ok(analisisBiometricoService.crear(analisisBiometrico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalisisBiometrico> actualizar(@PathVariable Long id, @Validated @RequestBody AnalisisBiometrico analisisBiometrico) {
        log.info("Petición REST recibida para actualizar análisis con ID: {}", id);
        return ResponseEntity.ok(analisisBiometricoService.actualizar(analisisBiometrico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Petición REST recibida para eliminar análisis con ID: {}", id);
        analisisBiometricoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}