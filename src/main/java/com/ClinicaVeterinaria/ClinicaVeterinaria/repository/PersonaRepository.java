package com.ClinicaVeterinaria.ClinicaVeterinaria.repository;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Long > {
}
