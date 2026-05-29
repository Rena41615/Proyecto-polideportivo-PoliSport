package com.polisport.iam.repository;

import com.polisport.iam.model.PermisosRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisosRolRepository extends JpaRepository<PermisosRol, Long> {
}
