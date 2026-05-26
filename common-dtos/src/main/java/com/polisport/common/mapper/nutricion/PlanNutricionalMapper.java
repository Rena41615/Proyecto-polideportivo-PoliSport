package com.polisport.common.mapper.nutricion;

import com.polisport.nutricion.model.PlanNutricional;
import com.polisport.common.dto.nutricion.*;
import org.springframework.stereotype.Component;

@Component
public class PlanNutricionalMapper {

    public PlanNutricional crearDTOToEntity(PlanNutricionalCrearDTO dto) {
        if (dto == null) return null;
        
        PlanNutricional entity = new PlanNutricional();
        entity.setAtletaId(dto.getAtletaId());
        entity.setObjetivo(dto.getObjetivo());
        entity.setEstado(dto.getEstado());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setCaloriasDiarias(dto.getCaloriasDiarias());
        entity.setObservaciones(dto.getObservaciones());
        
        return entity;
    }

    public PlanNutricionalDTO entityToDTO(PlanNutricional entity) {
        if (entity == null) return null;
        
        PlanNutricionalDTO dto = new PlanNutricionalDTO();
        dto.setId(entity.getId());
        dto.setAtletaId(entity.getAtletaId());
        dto.setObjetivo(entity.getObjetivo());
        dto.setEstado(entity.getEstado());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFin(entity.getFechaFin());
        dto.setCaloriasDiarias(entity.getCaloriasDiarias());
        dto.setObservaciones(entity.getObservaciones());
        
        return dto;
    }
}
