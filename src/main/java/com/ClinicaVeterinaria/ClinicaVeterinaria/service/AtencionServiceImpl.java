package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Atencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.AtencionRepository;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.MascotaRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtencionServiceImpl implements AtencionService {

    //inyectamos el repo
    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private MascotaService mascotaService;

    @Override
    public List<Atencion> findAllAtencionesByMascota(Long id_mascota) {
        Mascota mascota = mascotaService.findMascotaById(id_mascota)
                .orElseThrow(() -> new NoSuchElementException("Mascota no encontrada con el ID: " + id_mascota));//agregamos exception por si no encuentra el id

        return mascota.getListaAtenciones(); // Retornamos lista de atenciones de la mascota
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
        Atencion atencionDB = atencionRepository.findById(id_atencion).get();
        atencionDB.setTitulo(atencion.getTitulo());
        atencionDB.setDetalle_atencion(atencion.getDetalle_atencion());
        atencionRepository.save(atencion);
    }

    @Override
    public void deleteAtencion(Long id_atencion) {
        atencionRepository.deleteById(id_atencion);
    }

}
