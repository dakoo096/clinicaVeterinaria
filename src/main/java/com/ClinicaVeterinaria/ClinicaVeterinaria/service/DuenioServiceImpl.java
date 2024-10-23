
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Duenio;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.DuenioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuenioServiceImpl implements DuenioService{
    //inyectamos el repo
    @Autowired
    private DuenioRepository duenioRepository;
    @Override
    public List<Duenio> findAllDuenios() {
        return duenioRepository.findAll();
    }

    @Override
    public Optional<Duenio> findDuenioById(Long id_duenio) {
        return duenioRepository.findById(id_duenio);
    }

    @Override
    public Duenio saveDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    @Override
    public void updateDuenio(Long id_duenio, Duenio duenio) {
        Duenio duenioBD = duenioRepository.findById(id_duenio).get();// obtenemos el objeto con get
        duenioBD.setNombre_duenio(duenio.getNombre_duenio());
        duenioBD.setApellido(duenio.getApellido());
        duenio.setCelular(duenio.getCelular());
        duenioRepository.save(duenio);
    }

    @Override
    public void deleteDuenio(Long id_duenio) {
        duenioRepository.deleteById(id_duenio);
    }
    
}
