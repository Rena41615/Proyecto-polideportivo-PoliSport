package com.polisport.iam.mapper;

import com.polisport.iam.model.Usuarios;
import com.polisport.iam.dto.*;
import org.springframework.stereotype.Component;

@Component
public class UsuariosMapper {

    public Usuarios crearDTOToEntity(UsuariosCrearDTO dto) {
        if (dto == null) return null;
        
        Usuarios entity = new Usuarios();
        entity.setEmail(dto.getEmail());
        entity.setPasswordHash(dto.getContrasena());
        entity.setNombre(dto.getNombre());
        
        return entity;
    }

    public UsuariosDTO entityToDTO(Usuarios entity) {
        if (entity == null) return null;
        
        UsuariosDTO dto = new UsuariosDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNombre(entity.getNombre());
        dto.setContrasena(entity.getPasswordHash());
        
        return dto;
    }
}
