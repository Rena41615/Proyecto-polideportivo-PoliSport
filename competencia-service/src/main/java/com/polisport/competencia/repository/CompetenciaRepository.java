package com.polisport.competencia.repository;

import com.polisport.competencia.model.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface CompetenciaRepository extends JpaRepository<Competencia, Long>{

    @Query("SELECT c FROM Competencia c JOIN c.inscritosRun i WHERE i = :run")
    List<Competencia> buscarPorAtletaInscrito(@Param("run") Integer run);

}