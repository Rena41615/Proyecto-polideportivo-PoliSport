package com.polisport.common.mapper.competencia;

import com.polisport.competencia.model.Competencia;
import com.polisport.common.dto.competencia.*;
import org.springframework.stereotype.Component;

@Component
public class CompetenciaMapper {

    public Competencia crearDTOToEntity(CompetenciaCrearDTO dto) {
        if (dto == null) return null;
        
        Competencia entity = new Competencia();
        entity.setNombreCompetencia(dto.getNombreCompetencia());
        entity.setLugarCompetencia(dto.getLugarCompetencia());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setCategoria(dto.getCategoria());
        entity.setModalidad(dto.getModalidad());
        entity.setEstadoCompetencia(dto.getEstadoCompetencia());
        entity.setInscritosRun(dto.getInscritosRun());
        entity.setResultadosDetalle(dto.getResultadosDetalle());
        
        return entity;
    }

    public CompetenciaDTO entityToDTO(Competencia entity) {
        if (entity == null) return null;
        
        CompetenciaDTO dto = new CompetenciaDTO();
        dto.setId(entity.getId());
        dto.setNombreCompetencia(entity.getNombreCompetencia());
        dto.setLugarCompetencia(entity.getLugarCompetencia());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFin(entity.getFechaFin());
        dto.setCategoria(entity.getCategoria());
        dto.setModalidad(entity.getModalidad());
        dto.setEstadoCompetencia(entity.getEstadoCompetencia());
        dto.setInscritosRun(entity.getInscritosRun());
        dto.setResultadosDetalle(entity.getResultadosDetalle());
        
        return dto;
    }
}
