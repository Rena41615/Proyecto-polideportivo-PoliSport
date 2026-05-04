package com.polisport.salud.controller;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.service.GestionMedicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salud")
public class GestionMedicaController {

    private final GestionMedicaService gestionMedicaService;

    public GestionMedicaController(GestionMedicaService gestionMedicaService) {
        this.gestionMedicaService = gestionMedicaService;
    }

    @GetMapping
    public ResponseEntity<List<GestionMedica>> listar() {
        return ResponseEntity.ok(gestionMedicaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestionMedica> buscarPorId(@PathVariable Long id) {
        return gestionMedicaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GestionMedica> crear(@RequestBody GestionMedica gestionMedica) {
        return ResponseEntity.ok(gestionMedicaService.crear(gestionMedica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestionMedica> actualizar(@PathVariable Long id, @RequestBody GestionMedica gestionMedica) {
        return ResponseEntity.ok(gestionMedicaService.actualizar(gestionMedica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        gestionMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}