package com.ClinicaVeterinaria.ClinicaVeterinaria.repository;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RazaRepository extends JpaRepository<Raza, Long> {
    boolean existsByNombre(String nombre);
}