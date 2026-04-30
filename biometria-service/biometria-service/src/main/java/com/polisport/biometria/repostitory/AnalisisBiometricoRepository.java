package com.polisport.biometria.repostitory;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisBiometricoRepository extends JpaRepository<Long, Id> {

}