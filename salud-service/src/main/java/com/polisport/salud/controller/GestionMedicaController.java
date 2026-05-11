package com.polisport.salud.controller;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.service.GestionMedicaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/salud")
public class GestionMedicaController {

    private final GestionMedicaService gestionMedicaService;

    public GestionMedicaController(GestionMedicaService gestionMedicaService) {
        this.gestionMedicaService = gestionMedicaService;
    }

    @GetMapping
    public ResponseEntity<List<GestionMedica>> listar() {
        log.info("Petición REST recibida para listar gestiones médicas");
        return ResponseEntity.ok(gestionMedicaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestionMedica> buscarPorId(@PathVariable Long id) {
        log.info("Petición REST recibida para buscar gestión médica con ID: {}", id);
        return gestionMedicaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.error("Gestión médica con ID {} no encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<GestionMedica> crear(@Validated @RequestBody GestionMedica gestionMedica) {
        log.info("Petición REST recibida para crear un registro médico");
        return ResponseEntity.ok(gestionMedicaService.crear(gestionMedica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestionMedica> actualizar(@PathVariable Long id, @Validated @RequestBody GestionMedica gestionMedica) {
        log.info("Petición REST recibida para actualizar registro médico con ID: {}", id);
        return ResponseEntity.ok(gestionMedicaService.actualizar(gestionMedica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Petición REST recibida para eliminar registro médico con ID: {}", id);
        gestionMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}