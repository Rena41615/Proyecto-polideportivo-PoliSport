package com.polisport.inventario.controller;

import com.polisport.inventario.dto.InventarioDTO;
import com.polisport.inventario.dto.InventarioCrearDTO;
import com.polisport.inventario.mapper.InventarioMapper;
import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.service.InventarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
@CrossOrigin(origins = "*")
@Tag(name = "Inventario", description = "Operaciones CRUD para equipamiento")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioMapper inventarioMapper;

    public InventarioController(InventarioService inventarioService, InventarioMapper inventarioMapper) {
        this.inventarioService = inventarioService;
        this.inventarioMapper = inventarioMapper;
    }

    @Operation(summary = "Listar todo el equipamiento", description = "Obtiene el listado completo de todos los equipos almacenados en el inventario")
    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar() {
        List<Inventario> inventarios = inventarioService.listar();
        List<InventarioDTO> dtos = inventarios.stream()
                .map(inventarioMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Buscar equipamiento por ID", description = "Obtiene los detalles de un equipo específico del inventario usando su identificador único")
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> buscarPorId(@PathVariable Long id) {
        return inventarioService.buscarPorId(id)
                .map(inventario -> ResponseEntity.ok(inventarioMapper.entityToDTO(inventario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo equipamiento", description = "Crea e inserta un nuevo equipamiento en el inventario con los datos proporcionados")
    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@Valid @RequestBody InventarioCrearDTO crearDTO) {
        Inventario inventario = inventarioMapper.crearDTOToEntity(crearDTO);
        Inventario creado = inventarioService.crear(inventario);
        return new ResponseEntity<>(inventarioMapper.entityToDTO(creado), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar equipamiento", description = "Actualiza los datos de un equipamiento existente en el inventario usando su identificador único")
    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InventarioCrearDTO crearDTO) {
        Inventario inventario = inventarioMapper.crearDTOToEntity(crearDTO);
        inventario.setId(id);
        Inventario actualizado = inventarioService.actualizar(inventario);
        return ResponseEntity.ok(inventarioMapper.entityToDTO(actualizado));
    }

    @Operation(summary = "Eliminar equipamiento", description = "Elimina un equipamiento del inventario usando su identificador único")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}