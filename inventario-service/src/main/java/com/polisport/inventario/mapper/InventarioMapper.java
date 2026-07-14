package com.polisport.inventario.mapper;

import com.polisport.inventario.model.Inventario;
import com.polisport.inventario.dto.*;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public Inventario crearDTOToEntity(InventarioCrearDTO dto) {
        if (dto == null) return null;

        Inventario entity = new Inventario();
        entity.setNombre(dto.getNombreArticulo());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCantidad(dto.getCantidad());
        entity.setUbicacion(dto.getUbicacion());
        entity.setEstado(dto.getEstado());
        entity.setInstalacionId(dto.getInstalacionId());

        return entity;
    }

    public InventarioDTO entityToDTO(Inventario entity) {
        if (entity == null) return null;

        InventarioDTO dto = new InventarioDTO();
        dto.setId(entity.getId());
        dto.setNombreArticulo(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCantidad(entity.getCantidad());
        dto.setUbicacion(entity.getUbicacion());

        return dto;
    }
}