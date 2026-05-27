package com.polisport.iam.mapper;

import com.polisport.iam.model.Rol;
import com.polisport.iam.dto.*;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public Rol crearDTOToEntity(RolCrearDTO dto) {
        if (dto == null) return null;
        
        Rol entity = new Rol();
        entity.setNombre(dto.getNombreRol());
        entity.setDescripcion(dto.getDescripcion());
        
        return entity;
    }

    public RolDTO entityToDTO(Rol entity) {
        if (entity == null) return null;
        
        RolDTO dto = new RolDTO();
        dto.setId(entity.getId());
        dto.setNombreRol(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        
        return dto;
    }
}
