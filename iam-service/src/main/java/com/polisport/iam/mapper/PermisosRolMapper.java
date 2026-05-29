package com.polisport.iam.mapper;

import com.polisport.iam.model.PermisosRol;
import com.polisport.iam.dto.*;
import org.springframework.stereotype.Component;

@Component
public class PermisosRolMapper {

    public PermisosRolDTO entityToDTO(PermisosRol entity) {
        if (entity == null) return null;

        PermisosRolDTO dto = new PermisosRolDTO();
        dto.setId(entity.getId());
        dto.setRolId(entity.getRol().getId());
        dto.setRolNombre(entity.getRol().getNombre());
        dto.setPermisoId(entity.getPermiso().getId());
        dto.setPermisoNombre(entity.getPermiso().getNombre());

        return dto;
    }

    public PermisosRol crearDTOToEntity(PermisosRolCrearDTO dto) {
        if (dto == null) return null;

        PermisosRol entity = new PermisosRol();
        // rol y permiso se asignan en el servicio/controller
        return entity;
    }
}