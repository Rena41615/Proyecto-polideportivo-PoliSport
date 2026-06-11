package com.polisport.entrenamiento.controller;

import com.polisport.entrenamiento.dto.EntrenamientoDTO;
import com.polisport.entrenamiento.dto.EntrenamientoCrearDTO;
import com.polisport.entrenamiento.mapper.EntrenamientoMapper;
import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.entrenamiento.service.EntrenamientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenamientos")
@CrossOrigin(origins = "*")
@Tag(name = "Entrenamiento", description = "Operaciones CRUD para entrenamientos")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @Autowired
    private EntrenamientoMapper entrenamientoMapper;

    @GetMapping
    @Operation(summary = "Listar todos los entrenamientos", description = "Obtiene la lista completa de todos los entrenamientos registrados en el sistema")
    public ResponseEntity<List<EntrenamientoDTO>> listarTodos() {
        List<Entrenamiento> entrenamientos = entrenamientoService.obtenerTodos();
        List<EntrenamientoDTO> dtos = entrenamientos.stream()
                .map(entrenamientoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar entrenamiento por ID", description = "Obtiene los detalles de un entrenamiento específico usando su identificador único")
    public ResponseEntity<EntrenamientoDTO> buscarPorId(@PathVariable Long id){
        return entrenamientoService.obtenerPorId(id)
                .map(entrenamiento -> ResponseEntity.ok(entrenamientoMapper.entityToDTO(entrenamiento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atleta/{run}")
    @Operation(summary = "Listar entrenamientos por atleta", description = "Obtiene todos los entrenamientos realizados por un atleta específico identificado por su RUN")
    public ResponseEntity<List<EntrenamientoDTO>> listarPorAtleta(@PathVariable Integer run){
        List<Entrenamiento> entrenamientos = entrenamientoService.listarPorAtleta(run);
        List<EntrenamientoDTO> dtos = entrenamientos.stream()
                .map(entrenamientoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo entrenamiento", description = "Crea un nuevo registro de entrenamiento con la información proporcionada")
    public ResponseEntity<EntrenamientoDTO> crear(@Valid @RequestBody EntrenamientoCrearDTO crearDTO) {
        Entrenamiento entrenamiento = entrenamientoMapper.crearDTOToEntity(crearDTO);
        Entrenamiento nuevo = entrenamientoService.guardar(entrenamiento);
        return new ResponseEntity<>(entrenamientoMapper.entityToDTO(nuevo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar entrenamiento existente", description = "Actualiza los datos de un entrenamiento específico identificado por su ID")
    public ResponseEntity<EntrenamientoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EntrenamientoCrearDTO crearDTO) {
        Entrenamiento entrenamiento = entrenamientoMapper.crearDTOToEntity(crearDTO);
        entrenamiento.setId(id);
        Entrenamiento actualizado = entrenamientoService.guardar(entrenamiento);
        return ResponseEntity.ok(entrenamientoMapper.entityToDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrenamiento", description = "Elimina un entrenamiento específico del sistema identificado por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entrenamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}