package com.polisport.inventario.mapper;

import com.polisport.inventario.model.Instalacion;
import com.polisport.inventario.dto.*;
import org.springframework.stereotype.Component;

@Component
public class InstalacionMapper {

    public Instalacion crearDTOToEntity(InstalacionCrearDTO dto) {
        if (dto == null) return null;

        Instalacion entity = new Instalacion();
        entity.setNombre(dto.getNombreInstalacion());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUbicacion(dto.getUbicacion());
        entity.setEstado(dto.getEstado());

        return entity;
    }

    public InstalacionDTO entityToDTO(Instalacion entity) {
        if (entity == null) return null;

        InstalacionDTO dto = new InstalacionDTO();
        dto.setId(entity.getId());
        dto.setNombreInstalacion(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setUbicacion(entity.getUbicacion());
        dto.setEstado(entity.getEstado());

        return dto;
    }
}