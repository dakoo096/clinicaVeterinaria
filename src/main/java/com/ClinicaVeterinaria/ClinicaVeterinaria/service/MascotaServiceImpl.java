package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void deleteById(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public Mascota update(Long id, Mascota mascota) {
        return mascotaRepository.findById(id).map(m -> {
            m.setNombre(mascota.getNombre());
            m.setFecha_nacimiento(mascota.getFecha_nacimiento());
            m.setColor(mascota.getColor());
            m.setSexo(mascota.getSexo());
            m.setPeso(mascota.getPeso());
            m.setPersona(mascota.getPersona());
            m.setRaza(mascota.getRaza());
            return mascotaRepository.save(m);
        }).orElse(null);
    }
}
