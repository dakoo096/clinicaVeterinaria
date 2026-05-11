package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<Mascota> findAll();

    Optional<Mascota> findById(Long id);

    Mascota save(Mascota mascota);

    void deleteById(Long id);

    Mascota update(Long id, Mascota mascota);
}
