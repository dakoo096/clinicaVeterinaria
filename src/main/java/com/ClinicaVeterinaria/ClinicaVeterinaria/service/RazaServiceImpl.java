package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Raza;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RazaServiceImpl implements RazaService {

    @Autowired
    private RazaRepository razaRepository;

    @Override
    public List<Raza> findAll() {
        return razaRepository.findAll();
    }

    @Override
    public Optional<Raza> findById(Long id) {
        return razaRepository.findById(id);
    }

    @Override
    public Raza save(Raza raza) {
        return razaRepository.save(raza);
    }

    @Override
    public void deleteById(Long id) {
        razaRepository.deleteById(id);
    }

    @Override
    public Raza update(Long id, Raza raza) {
        return razaRepository.findById(id).map(r -> {
            r.setNombre(raza.getNombre());
            r.setEspecie(raza.getEspecie());
            return razaRepository.save(r);
        }).orElse(null);
    }
}
