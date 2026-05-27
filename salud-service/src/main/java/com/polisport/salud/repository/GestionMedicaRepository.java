package com.polisport.salud.repository;

import com.polisport.salud.model.GestionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionMedicaRepository extends JpaRepository<GestionMedica, Long> {

}