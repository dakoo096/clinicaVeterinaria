package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Atencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.AtencionRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private MascotaService mascotaService;

    @Override
    public List<Atencion> findAllAtencionesByMascota(Long id_mascota) {
        Mascota mascota = mascotaService.findById(id_mascota)
                .orElseThrow(() -> new NoSuchElementException("Mascota no encontrada con el ID: " + id_mascota));

        return mascota.getListaAtenciones();
    }

    @Override
    public Optional<Atencion> findAtencionById(Long id_atencion) {
        return atencionRepository.findById(id_atencion);
    }

    @Override
    public Atencion saveAtencion(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    @Override
    public void updateAtencion(Long id_atencion, Atencion atencion) {
        atencionRepository.findById(id_atencion).ifPresent(atencionDB -> {
            if (atencion.getFecha() != null) {
                atencionDB.setFecha(atencion.getFecha());
            }
            atencionDB.setDiagnostico(atencion.getDiagnostico());
            atencionDB.setTratamiento(atencion.getTratamiento());
            atencionDB.setObservaciones(atencion.getObservaciones());
            atencionRepository.save(atencionDB);
        });
    }

    @Override
    public void deleteAtencion(Long id_atencion) {
        atencionRepository.deleteById(id_atencion);
    }
}
