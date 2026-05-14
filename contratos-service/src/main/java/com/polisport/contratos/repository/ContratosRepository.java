package com.polisport.contratos.repository;

import com.polisport.contratos.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratosRepository extends JpaRepository<Contrato, Long> {

    // Busca historial de contratos de un empleado en especifico
    List<Contrato> findByRunEmpleado(Integer runEmpleado);
}