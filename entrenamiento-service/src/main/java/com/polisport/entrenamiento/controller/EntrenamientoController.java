package com.polisport.entrenamiento.controller;

import com.polisport.common.dto.entrenamiento.EntrenamientoDTO;
import com.polisport.common.dto.entrenamiento.EntrenamientoCrearDTO;
import com.polisport.common.mapper.entrenamiento.EntrenamientoMapper;
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
@CrossOrigin(origins = "*")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @Autowired
    private EntrenamientoMapper entrenamientoMapper;

    @GetMapping
    public ResponseEntity<List<EntrenamientoDTO>> listarTodos() {
        List<Entrenamiento> entrenamientos = entrenamientoService.obtenerTodos();
        List<EntrenamientoDTO> dtos = entrenamientos.stream()
                .map(entrenamientoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenamientoDTO> buscarPorId(@PathVariable Long id){
        return entrenamientoService.obtenerPorId(id)
                .map(entrenamiento -> ResponseEntity.ok(entrenamientoMapper.entityToDTO(entrenamiento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atleta/{run}")
    public ResponseEntity<List<EntrenamientoDTO>> listarPorAtleta(@PathVariable Integer run){
        List<Entrenamiento> entrenamientos = entrenamientoService.listarPorAtleta(run);
        List<EntrenamientoDTO> dtos = entrenamientos.stream()
                .map(entrenamientoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EntrenamientoDTO> crear(@Valid @RequestBody EntrenamientoCrearDTO crearDTO) {
        Entrenamiento entrenamiento = entrenamientoMapper.crearDTOToEntity(crearDTO);
        Entrenamiento nuevo = entrenamientoService.guardar(entrenamiento);
        return new ResponseEntity<>(entrenamientoMapper.entityToDTO(nuevo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenamientoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EntrenamientoCrearDTO crearDTO) {
        Entrenamiento entrenamiento = entrenamientoMapper.crearDTOToEntity(crearDTO);
        entrenamiento.setId(id);
        Entrenamiento actualizado = entrenamientoService.guardar(entrenamiento);
        return ResponseEntity.ok(entrenamientoMapper.entityToDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entrenamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}