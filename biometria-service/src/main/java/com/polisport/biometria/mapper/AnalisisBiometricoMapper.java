package com.polisport.biometria.mapper;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.dto.*;
import org.springframework.stereotype.Component;

/**
 * AnalisisBiometricoMapper: Convierte entre DTO y entidad.
 */
@Component
public class AnalisisBiometricoMapper {

    public AnalisisBiometrico crearDTOToEntity(AnalisisBiometricoCrearDTO dto) {
        if (dto == null) return null;
        
        AnalisisBiometrico entity = new AnalisisBiometrico();
        entity.setAtletaId(dto.getAtletaId());
        entity.setFecha(dto.getFecha());
        entity.setPeso(dto.getPeso() != null ? dto.getPeso() : 0);
        entity.setAltura(dto.getAltura() != null ? dto.getAltura() : 0);
        entity.setImc(dto.getImc() != null ? dto.getImc() : 0);
        entity.setPorcentajeGrasa(dto.getPorcentajeGrasa() != null ? dto.getPorcentajeGrasa() : 0);
        entity.setMasaMuscular(dto.getMasaMuscular() != null ? dto.getMasaMuscular() : 0);
        entity.setVo2Max(dto.getVo2Max() != null ? dto.getVo2Max() : 0);
        entity.setFrecuenciaCardiacaReposo(dto.getFrecuenciaCardiacaReposo() != null ? dto.getFrecuenciaCardiacaReposo() : 0);
        entity.setIndicadorRendimiento(dto.getIndicadorRendimiento() != null ? dto.getIndicadorRendimiento() : 0);
        
        return entity;
    }

    public AnalisisBiometricoDTO entityToDTO(AnalisisBiometrico entity) {
        if (entity == null) return null;
        
        AnalisisBiometricoDTO dto = new AnalisisBiometricoDTO();
        dto.setId(entity.getId());
        dto.setAtletaId(entity.getAtletaId());
        dto.setFecha(entity.getFecha());
        dto.setPeso(entity.getPeso());
        dto.setAltura(entity.getAltura());
        dto.setImc(entity.getImc());
        dto.setPorcentajeGrasa(entity.getPorcentajeGrasa());
        dto.setMasaMuscular(entity.getMasaMuscular());
        dto.setVo2Max(entity.getVo2Max());
        dto.setFrecuenciaCardiacaReposo(entity.getFrecuenciaCardiacaReposo());
        dto.setIndicadorRendimiento(entity.getIndicadorRendimiento());
        
        return dto;
    }
}
