package com.polisport.contratos.mapper;

import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.dto.*;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper {

    public Contrato crearDTOToEntity(ContratoCrearDTO dto) {
        if (dto == null) return null;
        
        Contrato entity = new Contrato();
        entity.setRunEmpleado(dto.getRunEmpleado());
        entity.setDvrunEmpleado(dto.getDvrunEmpleado());
        entity.setCargo(dto.getCargo());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaTermino(dto.getFechaTermino());
        entity.setSalarioMensual(dto.getSalarioMensual());
        entity.setTipoContrato(dto.getTipoContrato());
        entity.setEstado(dto.getEstado());
        
        return entity;
    }

    public ContratoDTO entityToDTO(Contrato entity) {
        if (entity == null) return null;
        
        ContratoDTO dto = new ContratoDTO();
        dto.setId(entity.getId());
        dto.setRunEmpleado(entity.getRunEmpleado());
        dto.setDvrunEmpleado(entity.getDvrunEmpleado());
        dto.setCargo(entity.getCargo());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaTermino(entity.getFechaTermino());
        dto.setSalarioMensual(entity.getSalarioMensual());
        dto.setTipoContrato(entity.getTipoContrato());
        dto.setEstado(entity.getEstado());
        
        return dto;
    }
}
