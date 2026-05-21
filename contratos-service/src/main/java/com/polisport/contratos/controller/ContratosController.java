package com.polisport.contratos.controller;

import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.service.ContratosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratosController {

    @Autowired
    private ContratosService contratosService;

    @GetMapping
    public ResponseEntity<List<Contrato>> listarTodos() {
        return ResponseEntity.ok(contratosService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> buscarPorId(@PathVariable Long id) {
        return contratosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{run}")
    public ResponseEntity<List<Contrato>> listarPorEmpleado(@PathVariable Integer run) {
        return ResponseEntity.ok(contratosService.listarPorEmpleado(run));
    }

    @PostMapping
    public ResponseEntity<Contrato> crear(@Valid @RequestBody Contrato contrato) {
        Contrato nuevo = contratosService.guardar(contrato);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contratosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}