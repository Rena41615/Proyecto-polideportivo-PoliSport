package com.polisport.iam.mapper;

import com.polisport.iam.dto.RolesUsuariosCrearDTO;
import com.polisport.iam.dto.RolesUsuariosDTO;
import com.polisport.iam.model.RolesUsuarios;
import org.springframework.stereotype.Component;

@Component
public class RolesUsuariosMapper {

    public RolesUsuariosDTO entityToDTO(RolesUsuarios entity) {
        if (entity == null) return null;

        RolesUsuariosDTO dto = new RolesUsuariosDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setUsuarioEmail(entity.getUsuario().getEmail());
        dto.setUsuarioNombre(entity.getUsuario().getNombre());
        dto.setRolId(entity.getRol().getId());
        dto.setRolNombre(entity.getRol().getNombre());
        dto.setFechaAsignacion(entity.getFechaAsignacion());

        return dto;
    }

    public RolesUsuarios crearDTOToEntity(RolesUsuariosCrearDTO dto) {
        if (dto == null) return null;

        RolesUsuarios entity = new RolesUsuarios();
        return entity;
    }
}
