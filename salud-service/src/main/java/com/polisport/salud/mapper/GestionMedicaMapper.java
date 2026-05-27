package com.polisport.salud.mapper;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.dto.*;
import org.springframework.stereotype.Component;

@Component
public class GestionMedicaMapper {

    public GestionMedica crearDTOToEntity(GestionMedicaCrearDTO dto) {
        if (dto == null) return null;
        
        GestionMedica entity = new GestionMedica();
        entity.setAtletaId(dto.getAtletaId());
        entity.setTipoLesion(dto.getTipoLesion());
        entity.setFechaDiagnostico(dto.getFechaDiagnostico());
        entity.setDescripcion(dto.getDescripcion());
        entity.setTratamiento(dto.getTratamiento());
        entity.setObservaciones(dto.getObservaciones());
        
        return entity;
    }

    public GestionMedicaDTO entityToDTO(GestionMedica entity) {
        if (entity == null) return null;
        
        GestionMedicaDTO dto = new GestionMedicaDTO();
        dto.setId(entity.getId());
        dto.setAtletaId(entity.getAtletaId());
        dto.setTipoLesion(entity.getTipoLesion());
        dto.setFechaDiagnostico(entity.getFechaDiagnostico());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTratamiento(entity.getTratamiento());
        dto.setObservaciones(entity.getObservaciones());
        
        return dto;
    }
}
