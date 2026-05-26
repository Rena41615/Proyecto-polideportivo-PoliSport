package com.polisport.common.mapper.staff;

import com.polisport.staff.model.RolStaff;
import com.polisport.common.dto.staff.*;
import org.springframework.stereotype.Component;

@Component
public class RolStaffMapper {

    public RolStaff crearDTOToEntity(RolStaffCrearDTO dto) {
        if (dto == null) return null;
        
        RolStaff entity = new RolStaff();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        
        return entity;
    }

    public RolStaffDTO entityToDTO(RolStaff entity) {
        if (entity == null) return null;
        
        RolStaffDTO dto = new RolStaffDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        
        return dto;
    }
}
