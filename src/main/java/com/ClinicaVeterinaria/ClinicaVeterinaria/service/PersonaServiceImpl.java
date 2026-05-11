package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Persona;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona update(Long id, Persona persona) {
        return personaRepository.findById(id).map(p -> {
            p.setNombre(persona.getNombre());
            p.setApellido(persona.getApellido());
            p.setDni(persona.getDni());
            p.setTelefono(persona.getTelefono());
            p.setEmail(persona.getEmail());
            return personaRepository.save(p);
        }).orElse(null);
    }
}
