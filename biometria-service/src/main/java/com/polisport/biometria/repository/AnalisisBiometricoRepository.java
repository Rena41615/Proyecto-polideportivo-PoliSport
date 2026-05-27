package com.polisport.biometria.repository;

import com.polisport.biometria.model.AnalisisBiometrico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisBiometricoRepository extends JpaRepository<AnalisisBiometrico, Long> {

}