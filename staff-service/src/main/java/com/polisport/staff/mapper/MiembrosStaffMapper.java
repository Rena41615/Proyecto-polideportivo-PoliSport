package com.polisport.staff.mapper;

import com.polisport.staff.model.MiembrosStaff;
import com.polisport.staff.dto.*;
import org.springframework.stereotype.Component;

@Component
public class MiembrosStaffMapper {

    public MiembrosStaff crearDTOToEntity(MiembrosStaffCrearDTO dto) {
        if (dto == null) return null;
        
        MiembrosStaff entity = new MiembrosStaff();
        entity.setRun(dto.getRun());
        entity.setDv(dto.getDv());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDocumento(dto.getDocumento());
        entity.setEmail(dto.getEmail());
        entity.setPuesto(dto.getPuesto());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setObservaciones(dto.getObservaciones());
        
        return entity;
    }

    public MiembrosStaffDTO entityToDTO(MiembrosStaff entity) {
        if (entity == null) return null;
        
        MiembrosStaffDTO dto = new MiembrosStaffDTO();
        dto.setId(entity.getId());
        dto.setRun(entity.getRun());
        dto.setDv(entity.getDv());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDocumento(entity.getDocumento());
        dto.setEmail(entity.getEmail());
        dto.setPuesto(entity.getPuesto());
        dto.setFechaIngreso(entity.getFechaIngreso());
        dto.setObservaciones(entity.getObservaciones());
        
        return dto;
    }
}
