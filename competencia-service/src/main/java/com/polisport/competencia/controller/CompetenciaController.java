package com.polisport.competencia.controller;

import com.polisport.competencia.dto.CompetenciaDTO;
import com.polisport.competencia.dto.CompetenciaCrearDTO;
import com.polisport.competencia.mapper.CompetenciaMapper;
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
@CrossOrigin(origins = "*")
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @GetMapping
    public ResponseEntity<List<CompetenciaDTO>> listar() {
        List<Competencia> competencias = competenciaService.obtenerTodas();
        List<CompetenciaDTO> dtos = competencias.stream()
                .map(competenciaMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> buscar(@PathVariable Long id) {
        return competenciaService.obtenerPorId(id)
                .map(competencia -> ResponseEntity.ok(competenciaMapper.entityToDTO(competencia)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atleta/{run}")
    public ResponseEntity<List<CompetenciaDTO>> listarPorAtleta(@PathVariable Integer run) {
        List<Competencia> competencias = competenciaService.listarPorAtleta(run);
        List<CompetenciaDTO> dtos = competencias.stream()
                .map(competenciaMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<CompetenciaDTO> crear(@Valid @RequestBody CompetenciaCrearDTO crearDTO) {
        Competencia competencia = competenciaMapper.crearDTOToEntity(crearDTO);
        Competencia nueva = competenciaService.guardar(competencia);
        return new ResponseEntity<>(competenciaMapper.entityToDTO(nueva), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CompetenciaCrearDTO crearDTO) {
        Competencia competencia = competenciaMapper.crearDTOToEntity(crearDTO);
        competencia.setId(id);
        Competencia actualizada = competenciaService.guardar(competencia);
        return ResponseEntity.ok(competenciaMapper.entityToDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        competenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}