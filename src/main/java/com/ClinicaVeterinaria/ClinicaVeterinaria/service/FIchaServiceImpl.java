package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FIchaServiceImpl implements FichaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public FichaDTO buscarPorNombreDeMascota(String nombre) {
        Mascota mascota = mascotaRepository.findByNombre(nombre);
        if (mascota == null) {
            return null;
        }

        return mapToFichaDTO(mascota);
    }

    @Override
    public List<FichaDTO> obtenerFichas() {
        return mascotaRepository.findAll().stream()
                .map(this::mapToFichaDTO)
                .collect(Collectors.toList());
    }

    private FichaDTO mapToFichaDTO(Mascota mascota) {
        FichaDTO ficha = new FichaDTO();
        ficha.setId_mascota(mascota.getId_mascota());
        ficha.setNombre(mascota.getNombre());
        
        if (mascota.getRaza() != null) {
            ficha.setRaza(mascota.getRaza().getNombre());
            if (mascota.getRaza().getEspecie() != null) {
                ficha.setEspecie(mascota.getRaza().getEspecie().getNombre());
            }
        }
        
        ficha.setColor(mascota.getColor());
        
        if (mascota.getPersona() != null) {
            ficha.setNombre_duenio(mascota.getPersona().getNombre());
            ficha.setApellido_duenio(mascota.getPersona().getApellido());
            ficha.setCelular(mascota.getPersona().getTelefono());
            ficha.setDni(mascota.getPersona().getDni());
        }
        return ficha;
    }
}
