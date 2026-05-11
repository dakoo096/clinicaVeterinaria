package com.ClinicaVeterinaria.ClinicaVeterinaria.repository;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByFechaHoraInicioBetween(LocalDateTime start, LocalDateTime end);
}
