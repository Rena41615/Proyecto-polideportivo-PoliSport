package com.polisport.atletas.controller;

import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.service.AtletaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/atletas")

public class AtletaController {

    @Autowired
    private AtletaService atletaService;

    @GetMapping
    public ResponseEntity<List<Atleta>> listarTodos(){
        return ResponseEntity.ok(atletaService.obtenerTodos());
    }

    @GetMapping("/{id}")
        public ResponseEntity<Atleta> obtenerPorId(@PathVariable Long id) {
        return atletaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Atleta> obtenerPorRut(@PathVariable String rut){
        return atletaService.obtenerPorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atleta> guardar(@Valid@RequestBody Atleta atleta){
        Atleta nuevoAtleta = atletaService.guardarAtleta(atleta);
        return new ResponseEntity<>(nuevoAtleta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        atletaService.eliminarAtleta(id);
        return ResponseEntity.noContent().build();
    }

}
