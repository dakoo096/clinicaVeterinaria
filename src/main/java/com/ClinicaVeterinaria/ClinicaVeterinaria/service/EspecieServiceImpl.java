package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Especie;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieServiceImpl implements EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    @Override
    public List<Especie> findAll() {
        return especieRepository.findAll();
    }

    @Override
    public Optional<Especie> findById(Long id) {
        return especieRepository.findById(id);
    }

    @Override
    public Especie save(Especie especie) {
        return especieRepository.save(especie);
    }

    @Override
    public void deleteById(Long id) {
        especieRepository.deleteById(id);
    }

    @Override
    public Especie update(Long id, Especie especie) {
        return especieRepository.findById(id).map(e -> {
            e.setNombre(especie.getNombre());
            return especieRepository.save(e);
        }).orElse(null);
    }
}
