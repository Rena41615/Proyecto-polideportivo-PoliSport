package com.polisport.competencia.controller;

import com.polisport.competencia.model.Competencia;
import com.polisport.competencia.service.CompetenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/competencias")

public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping
    public ResponseEntity<List<Competencia>> listar() {
        return ResponseEntity.ok(competenciaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competencia> buscar(@PathVariable Long id) {
        return competenciaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atleta/{run}")
    public ResponseEntity<List<Competencia>> listarPorAtleta(@PathVariable Integer run) {
        return ResponseEntity.ok(competenciaService.listarPorAtleta(run));
    }

    @PostMapping
    public ResponseEntity<Competencia> crear(@Valid @RequestBody Competencia competencia) {
        Competencia nueva = competenciaService.guardar(competencia);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        competenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}