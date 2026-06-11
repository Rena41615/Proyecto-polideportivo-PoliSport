package com.polisport.competencia.controller;

import com.polisport.competencia.dto.CompetenciaDTO;
import com.polisport.competencia.dto.CompetenciaCrearDTO;
import com.polisport.competencia.mapper.CompetenciaMapper;
import com.polisport.competencia.model.Competencia;
import com.polisport.competencia.service.CompetenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/competencias")
@CrossOrigin(origins = "*")
@Tag(name = "Competencia", description = "Operaciones CRUD para competencias")
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @GetMapping
    @Operation(summary = "Listar todas las competencias", description = "Obtiene la lista completa de todas las competencias registradas en el sistema")
    public ResponseEntity<List<CompetenciaDTO>> listar() {
        List<Competencia> competencias = competenciaService.obtenerTodas();
        List<CompetenciaDTO> dtos = competencias.stream()
                .map(competenciaMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar competencia por ID", description = "Obtiene los detalles de una competencia específica usando su identificador único")
    public ResponseEntity<CompetenciaDTO> buscar(@PathVariable Long id) {
        return competenciaService.obtenerPorId(id)
                .map(competencia -> ResponseEntity.ok(competenciaMapper.entityToDTO(competencia)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atleta/{run}")
    @Operation(summary = "Listar competencias por atleta", description = "Obtiene todas las competencias en las que ha participado un atleta específico identificado por su RUN")
    public ResponseEntity<List<CompetenciaDTO>> listarPorAtleta(@PathVariable Integer run) {
        List<Competencia> competencias = competenciaService.listarPorAtleta(run);
        List<CompetenciaDTO> dtos = competencias.stream()
                .map(competenciaMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Crear nueva competencia", description = "Crea un nuevo registro de competencia con la información proporcionada")
    public ResponseEntity<CompetenciaDTO> crear(@Valid @RequestBody CompetenciaCrearDTO crearDTO) {
        Competencia competencia = competenciaMapper.crearDTOToEntity(crearDTO);
        Competencia nueva = competenciaService.guardar(competencia);
        return new ResponseEntity<>(competenciaMapper.entityToDTO(nueva), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar competencia existente", description = "Actualiza los datos de una competencia específica identificada por su ID")
    public ResponseEntity<CompetenciaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CompetenciaCrearDTO crearDTO) {
        Competencia competencia = competenciaMapper.crearDTOToEntity(crearDTO);
        competencia.setId(id);
        Competencia actualizada = competenciaService.guardar(competencia);
        return ResponseEntity.ok(competenciaMapper.entityToDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar competencia", description = "Elimina una competencia específica del sistema identificada por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        competenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}