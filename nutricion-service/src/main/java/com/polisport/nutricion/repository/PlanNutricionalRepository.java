package com.polisport.nutricion.repository;

import com.polisport.nutricion.model.PlanNutricional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanNutricionalRepository extends JpaRepository<PlanNutricional, Long> {

    List<PlanNutricional> findByAtletaId(Long atletaId);
}
