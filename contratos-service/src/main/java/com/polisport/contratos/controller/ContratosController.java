package com.polisport.contratos.controller;

import com.polisport.contratos.dto.ContratoDTO;
import com.polisport.contratos.dto.ContratoCrearDTO;
import com.polisport.contratos.mapper.ContratoMapper;
import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.service.ContratosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "*")
@Tag(name = "Contrato", description = "Operaciones CRUD para contratos de empleados")
public class ContratosController {

    @Autowired
    private ContratosService contratosService;

    @Autowired
    private ContratoMapper contratoMapper;

    @GetMapping
    @Operation(summary = "Listar todos los contratos", description = "Obtiene la lista completa de todos los contratos registrados en el sistema")
    public ResponseEntity<List<ContratoDTO>> listarTodos() {
        List<Contrato> contratos = contratosService.obtenerTodos();
        List<ContratoDTO> dtos = contratos.stream()
                .map(contratoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato por ID", description = "Obtiene los detalles de un contrato específico usando su identificador único")
    public ResponseEntity<ContratoDTO> buscarPorId(@PathVariable Long id) {
        return contratosService.obtenerPorId(id)
                .map(contrato -> ResponseEntity.ok(contratoMapper.entityToDTO(contrato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{run}")
    @Operation(summary = "Listar contratos por empleado", description = "Obtiene todos los contratos asociados a un empleado específico identificado por su RUN")
    public ResponseEntity<List<ContratoDTO>> listarPorEmpleado(@PathVariable Integer run) {
        List<Contrato> contratos = contratosService.listarPorEmpleado(run);
        List<ContratoDTO> dtos = contratos.stream()
                .map(contratoMapper::entityToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo contrato", description = "Crea un nuevo registro de contrato con la información proporcionada")
    public ResponseEntity<ContratoDTO> crear(@Valid @RequestBody ContratoCrearDTO crearDTO) {
        Contrato contrato = contratoMapper.crearDTOToEntity(crearDTO);
        Contrato nuevo = contratosService.guardar(contrato);
        return new ResponseEntity<>(contratoMapper.entityToDTO(nuevo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar contrato existente", description = "Actualiza los datos de un contrato específico identificado por su ID")
    public ResponseEntity<ContratoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ContratoCrearDTO crearDTO) {
        Contrato contrato = contratoMapper.crearDTOToEntity(crearDTO);
        contrato.setId(id);
        Contrato actualizado = contratosService.guardar(contrato);
        return ResponseEntity.ok(contratoMapper.entityToDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar contrato", description = "Elimina un contrato específico del sistema identificado por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contratosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}