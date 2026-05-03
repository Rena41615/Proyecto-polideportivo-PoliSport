package com.polisport.biometria.controller;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.service.AnalisisBiometricoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController// indica que es un controller Rest
@RequestMapping("/api/v1/biometria") //define ruta base
public class AnalisisBiometricoController {

    private final AnalisisBiometricoService analisisBiometricoService;

    public AnalisisBiometricoController(AnalisisBiometricoService analisisBiometricoService) {
        this.analisisBiometricoService = analisisBiometricoService;
    }

    @GetMapping
    public ResponseEntity<List<AnalisisBiometrico>> listar() {
        return ResponseEntity.ok(analisisBiometricoService.listar());

    }

}
