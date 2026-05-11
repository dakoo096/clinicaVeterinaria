package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Turno;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TurnoService {
    List<Turno> findAll();
    List<Turno> findByDateRange(LocalDateTime start, LocalDateTime end);
    Optional<Turno> findById(Long id);
    Turno save(Turno turno);
    void delete(Long id);
    Long confirmarAsistencia(Long idTurno);
}
