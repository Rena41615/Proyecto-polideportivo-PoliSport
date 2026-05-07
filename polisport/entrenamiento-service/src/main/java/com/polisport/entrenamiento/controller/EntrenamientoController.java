package com.polisport.entrenamiento.controller;

import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.entrenamiento.service.EntrenamientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenamientos")

public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @GetMapping
    public ResponseEntity<List<Entrenamiento>> listarTodos() {
        return ResponseEntity.ok(entrenamientoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Entrenamiento>> buscarPorId(@PathVariable Long id){
        return entrenamientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atleta/{run}")
    public ResponseEntity<List<Entrenamiento>> listarPorAtleta(@PathVariable Integer run){
        List<Entrenamiento> entrenamientos = entrenamientoService.listarPorAtleta(run);
        return ResponseEntity.ok(entrenamientos);
    }

    @PostMapping
    public ResponseEntity<Entrenamiento> crear(@Valid @RequestBody Entrenamiento entrenamiento) {
        Entrenamiento nuevo = entrenamientoService.guardar(entrenamiento);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entrenamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}