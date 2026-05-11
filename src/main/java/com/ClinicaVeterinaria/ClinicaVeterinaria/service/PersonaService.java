package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<Persona> findAll();
    Optional<Persona> findById(Long id);
    Persona save(Persona persona);
    void deleteById(Long id);
    Persona update(Long id, Persona persona);
}
