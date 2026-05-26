package com.polisport.common.mapper.atletas;

import com.polisport.atletas.model.Atleta;
import com.polisport.common.dto.atletas.*;
import org.springframework.stereotype.Component;

/**
 * AtletaMapper: Convierte entre entidades Atleta y DTOs.
 * Desacopla la API REST del modelo JPA.
 */
@Component
public class AtletaMapper {

    /**
     * Convierte un AtletaCrearDTO a entidad Atleta.
     * Se usa cuando recibimos datos del cliente para crear/actualizar.
     */
    public Atleta crearDTOToEntity(AtletaCrearDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Atleta atleta = new Atleta();
        atleta.setRunAtleta(dto.getRunAtleta());
        atleta.setDvrunAtleta(dto.getDvrunAtleta());
        atleta.setPrimerNombre(dto.getPrimerNombre());
        atleta.setSegundoNombre(dto.getSegundoNombre());
        atleta.setTercerNombre(dto.getTercerNombre());
        atleta.setPrimerApellido(dto.getPrimerApellido());
        atleta.setSegundoApellido(dto.getSegundoApellido());
        atleta.setEmail(dto.getEmail());
        atleta.setFechaNacimiento(dto.getFechaNacimiento());
        atleta.setDeportePrincipal(dto.getDeportePrincipal());
        atleta.setCategoria(dto.getCategoria());
        atleta.setHistorialDeportivo(dto.getHistorialDeportivo());
        
        return atleta;
    }

    /**
     * Convierte una entidad Atleta a AtletaDTO.
     * Se usa cuando enviamos datos al cliente.
     */
    public AtletaDTO entityToDTO(Atleta entity) {
        if (entity == null) {
            return null;
        }
        
        AtletaDTO dto = new AtletaDTO();
        dto.setId(entity.getId());
        dto.setRunAtleta(entity.getRunAtleta());
        dto.setDvrunAtleta(entity.getDvrunAtleta());
        dto.setPrimerNombre(entity.getPrimerNombre());
        dto.setSegundoNombre(entity.getSegundoNombre());
        dto.setTercerNombre(entity.getTercerNombre());
        dto.setPrimerApellido(entity.getPrimerApellido());
        dto.setSegundoApellido(entity.getSegundoApellido());
        dto.setEmail(entity.getEmail());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setDeportePrincipal(entity.getDeportePrincipal());
        dto.setCategoria(entity.getCategoria());
        dto.setHistorialDeportivo(entity.getHistorialDeportivo());
        
        return dto;
    }
}
