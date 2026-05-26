package com.polisport.contratos.controller;

import com.polisport.common.dto.contratos.ContratoDTO;
import com.polisport.common.dto.contratos.ContratoCrearDTO;
import com.polisport.common.mapper.contratos.ContratoMapper;
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
@CrossOrigin(origins = "*")
public class ContratosController {

    @Autowired
    private ContratosService contratosService;

    @Autowired
    private ContratoMapper contratoMapper;

    @GetMapping
    public ResponseEntity<List<ContratoDTO>> listarTodos() {
        List<Contrato> contratos = contratosService.obtenerTodos();
        List<ContratoDTO> dtos = contratos.stream()
                .map(contratoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> buscarPorId(@PathVariable Long id) {
        return contratosService.obtenerPorId(id)
                .map(contrato -> ResponseEntity.ok(contratoMapper.entityToDTO(contrato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{run}")
    public ResponseEntity<List<ContratoDTO>> listarPorEmpleado(@PathVariable Integer run) {
        List<Contrato> contratos = contratosService.listarPorEmpleado(run);
        List<ContratoDTO> dtos = contratos.stream()
                .map(contratoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ContratoDTO> crear(@Valid @RequestBody ContratoCrearDTO crearDTO) {
        Contrato contrato = contratoMapper.crearDTOToEntity(crearDTO);
        Contrato nuevo = contratosService.guardar(contrato);
        return new ResponseEntity<>(contratoMapper.entityToDTO(nuevo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ContratoCrearDTO crearDTO) {
        Contrato contrato = contratoMapper.crearDTOToEntity(crearDTO);
        contrato.setId(id);
        Contrato actualizado = contratosService.guardar(contrato);
        return ResponseEntity.ok(contratoMapper.entityToDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contratosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}