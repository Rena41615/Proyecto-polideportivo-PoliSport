package com.polisport.iam.mapper;

import com.polisport.iam.model.Permisos;
import com.polisport.iam.dto.*;
import org.springframework.stereotype.Component;

@Component
public class PermisosMapper {

    public Permisos crearDTOToEntity(PermisosCrearDTO dto) {
        if (dto == null) return null;

        Permisos entity = new Permisos();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        return entity;
    }

    public PermisosDTO entityToDTO(Permisos entity) {
        if (entity == null) return null;

        PermisosDTO dto = new PermisosDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }
}