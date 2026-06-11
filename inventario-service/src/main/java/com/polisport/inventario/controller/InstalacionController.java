package com.polisport.inventario.controller;

import com.polisport.inventario.dto.InstalacionDTO;
import com.polisport.inventario.dto.InstalacionCrearDTO;
import com.polisport.inventario.mapper.InstalacionMapper;
import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.service.InstalacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instalaciones")
@CrossOrigin(origins = "*")
@Tag(name = "Instalación", description = "Operaciones CRUD para instalaciones deportivas")
public class InstalacionController {

    private final InstalacionService instalacionService;
    private final InstalacionMapper instalacionMapper;

    public InstalacionController(InstalacionService instalacionService, InstalacionMapper instalacionMapper) {
        this.instalacionService = instalacionService;
        this.instalacionMapper = instalacionMapper;
    }

    @Operation(summary = "Listar todas las instalaciones", description = "Obtiene el listado completo de todas las instalaciones deportivas registradas")
    @GetMapping
    public ResponseEntity<List<InstalacionDTO>> listar() {
        List<Instalacion> instalaciones = instalacionService.listar();
        List<InstalacionDTO> dtos = instalaciones.stream()
                .map(instalacionMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Buscar instalación por ID", description = "Obtiene los detalles de una instalación deportiva específica usando su identificador único")
    @GetMapping("/{id}")
    public ResponseEntity<InstalacionDTO> buscarPorId(@PathVariable Long id) {
        return instalacionService.buscarPorId(id)
                .map(instalacion -> ResponseEntity.ok(instalacionMapper.entityToDTO(instalacion)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva instalación", description = "Crea e inserta una nueva instalación deportiva en el sistema con los datos proporcionados")
    @PostMapping
    public ResponseEntity<InstalacionDTO> crear(@Valid @RequestBody InstalacionCrearDTO crearDTO) {
        Instalacion instalacion = instalacionMapper.crearDTOToEntity(crearDTO);
        Instalacion creada = instalacionService.crear(instalacion);
        return new ResponseEntity<>(instalacionMapper.entityToDTO(creada), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar instalación", description = "Actualiza los datos de una instalación deportiva existente usando su identificador único")
    @PutMapping("/{id}")
    public ResponseEntity<InstalacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InstalacionCrearDTO crearDTO) {
        Instalacion instalacion = instalacionMapper.crearDTOToEntity(crearDTO);
        instalacion.setId(id);
        Instalacion actualizada = instalacionService.actualizar(instalacion);
        return ResponseEntity.ok(instalacionMapper.entityToDTO(actualizada));
    }

    @Operation(summary = "Eliminar instalación", description = "Elimina una instalación deportiva del sistema usando su identificador único")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        instalacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}