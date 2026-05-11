package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Especie;
import java.util.List;
import java.util.Optional;

public interface EspecieService {
    List<Especie> findAll();
    Optional<Especie> findById(Long id);
    Especie save(Especie especie);
    void deleteById(Long id);
    Especie update(Long id, Especie especie);
}
