package com.polisport.common.mapper.iam;

import com.polisport.iam.model.Rol;
import com.polisport.common.dto.iam.*;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public Rol crearDTOToEntity(RolCrearDTO dto) {
        if (dto == null) return null;
        
        Rol entity = new Rol();
        entity.setNombreRol(dto.getNombreRol());
        entity.setDescripcion(dto.getDescripcion());
        
        return entity;
    }

    public RolDTO entityToDTO(Rol entity) {
        if (entity == null) return null;
        
        RolDTO dto = new RolDTO();
        dto.setId(entity.getId());
        dto.setNombreRol(entity.getNombreRol());
        dto.setDescripcion(entity.getDescripcion());
        
        return dto;
    }
}
