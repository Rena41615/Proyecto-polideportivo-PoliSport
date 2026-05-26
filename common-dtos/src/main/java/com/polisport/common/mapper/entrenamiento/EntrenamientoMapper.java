package com.polisport.common.mapper.entrenamiento;

import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.common.dto.entrenamiento.*;
import org.springframework.stereotype.Component;

@Component
public class EntrenamientoMapper {

    public Entrenamiento crearDTOToEntity(EntrenamientoCrearDTO dto) {
        if (dto == null) return null;
        
        Entrenamiento entity = new Entrenamiento();
        entity.setTipoEntrenamiento(dto.getTipoEntrenamiento());
        entity.setFecha(dto.getFecha());
        entity.setHoraInicio(dto.getHoraInicio());
        entity.setHoraFin(dto.getHoraFin());
        entity.setEstado(dto.getEstado());
        entity.setAtletasParticipantes(dto.getAtletasParticipantes());
        entity.setObservaciones(dto.getObservaciones());
        entity.setNivelIntensidad(dto.getNivelIntensidad());
        
        return entity;
    }

    public EntrenamientoDTO entityToDTO(Entrenamiento entity) {
        if (entity == null) return null;
        
        EntrenamientoDTO dto = new EntrenamientoDTO();
        dto.setId(entity.getId());
        dto.setTipoEntrenamiento(entity.getTipoEntrenamiento());
        dto.setFecha(entity.getFecha());
        dto.setHoraInicio(entity.getHoraInicio());
        dto.setHoraFin(entity.getHoraFin());
        dto.setEstado(entity.getEstado());
        dto.setAtletasParticipantes(entity.getAtletasParticipantes());
        dto.setObservaciones(entity.getObservaciones());
        dto.setNivelIntensidad(entity.getNivelIntensidad());
        
        return dto;
    }
}
