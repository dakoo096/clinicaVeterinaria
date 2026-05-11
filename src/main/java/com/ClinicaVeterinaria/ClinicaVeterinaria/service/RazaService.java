package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Raza;
import java.util.List;
import java.util.Optional;

public interface RazaService {
    List<Raza> findAll();
    Optional<Raza> findById(Long id);
    Raza save(Raza raza);
    void deleteById(Long id);
    Raza update(Long id, Raza raza);
}
