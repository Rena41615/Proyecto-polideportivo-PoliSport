package com.polisport.inventario.repository;

import com.polisport.inventario.model.Instalacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalacionRepository extends JpaRepository<Instalacion, Long> {
}