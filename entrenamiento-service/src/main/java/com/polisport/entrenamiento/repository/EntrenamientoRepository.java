package com.polisport.entrenamiento.repository;

import com.polisport.entrenamiento.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long>{

    @Query("SELECT e FROM Entrenamiento e JOIN e.atletasParticipantes p WHERE p = :runAtleta")
    List<Entrenamiento> buscarPorAtleta(@Param("runAtleta") Integer runAtleta);

    List<Entrenamiento> findByRunEntrenador(Integer runEntrenador);

}