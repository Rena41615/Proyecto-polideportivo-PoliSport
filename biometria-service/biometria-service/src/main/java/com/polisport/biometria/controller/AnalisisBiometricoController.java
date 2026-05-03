package com.polisport.biometria.controller;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.service.AnalisisBiometricoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// indica que es un controller Rest
@RequestMapping("/api/v1/biometria") //define ruta base
public class AnalisisBiometricoController {

    private final AnalisisBiometricoService analisisBiometricoService;

    public AnalisisBiometricoController(AnalisisBiometricoService analisisBiometricoService) {
        this.analisisBiometricoService = analisisBiometricoService;
    }

    @GetMapping
    public ResponseEntity<List<AnalisisBiometrico>> listar() {
        return ResponseEntity.ok(analisisBiometricoService.listar());

    }
    @GetMapping("/{id}")
    public ResponseEntity<AnalisisBiometrico> buscarPorId(@PathVariable Long id) {
        return analisisBiometricoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<AnalisisBiometrico> crear(@RequestBody AnalisisBiometrico analisisBiometrico) {
        return ResponseEntity.ok(analisisBiometricoService.crear(analisisBiometrico));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AnalisisBiometrico> actualizar(@PathVariable Long id, @RequestBody AnalisisBiometrico analisisBiometrico){
        return ResponseEntity.ok(analisisBiometricoService.actualizar(analisisBiometrico));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        analisisBiometricoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
