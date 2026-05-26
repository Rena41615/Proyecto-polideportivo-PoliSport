package com.polisport.inventario.controller;

import com.polisport.common.dto.inventario.InventarioDTO;
import com.polisport.common.dto.inventario.InventarioCrearDTO;
import com.polisport.common.mapper.inventario.InventarioMapper;
import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioMapper inventarioMapper;

    public InventarioController(InventarioService inventarioService, InventarioMapper inventarioMapper) {
        this.inventarioService = inventarioService;
        this.inventarioMapper = inventarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar() {
        List<Inventario> inventarios = inventarioService.listar();
        List<InventarioDTO> dtos = inventarios.stream()
                .map(inventarioMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> buscarPorId(@PathVariable Long id) {
        return inventarioService.buscarPorId(id)
                .map(inventario -> ResponseEntity.ok(inventarioMapper.entityToDTO(inventario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@Valid @RequestBody InventarioCrearDTO crearDTO) {
        Inventario inventario = inventarioMapper.crearDTOToEntity(crearDTO);
        Inventario creado = inventarioService.crear(inventario);
        return new ResponseEntity<>(inventarioMapper.entityToDTO(creado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InventarioCrearDTO crearDTO) {
        Inventario inventario = inventarioMapper.crearDTOToEntity(crearDTO);
        inventario.setId(id);
        Inventario actualizado = inventarioService.actualizar(inventario);
        return ResponseEntity.ok(inventarioMapper.entityToDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}