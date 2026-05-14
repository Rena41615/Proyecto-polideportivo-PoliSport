package com.polisport.inventario.controller;

import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.service.InstalacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instalaciones")
public class InstalacionController {

    private final InstalacionService instalacionService;

    public InstalacionController(InstalacionService instalacionService) {
        this.instalacionService = instalacionService;
    }

    @GetMapping
    public ResponseEntity<List<Instalacion>> listar() {
        return ResponseEntity.ok(instalacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instalacion> buscarPorId(@PathVariable Long id) {
        return instalacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instalacion> crear(@RequestBody Instalacion instalacion) {
        return ResponseEntity.ok(instalacionService.crear(instalacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instalacion> actualizar(@PathVariable Long id, @RequestBody Instalacion instalacion) {
        return ResponseEntity.ok(instalacionService.actualizar(instalacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        instalacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}