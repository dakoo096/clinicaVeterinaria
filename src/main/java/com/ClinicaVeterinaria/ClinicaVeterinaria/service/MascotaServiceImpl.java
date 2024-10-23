package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Duenio;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.MascotaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaServiceImpl implements MascotaService {

    //inyectamos el repo
    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<Mascota> findAllMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> findMascotaById(Long id_mascota) {
        return mascotaRepository.findById(id_mascota);
    }

    @Override
    public Mascota saveMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void updateMascota(Long id_mascota, Mascota mascota) {
        //primero obtenemos la mascota y luego lo sobreescribimos
        Mascota mascotaBD = findMascotaById(id_mascota).get();
        mascotaBD.setNombre(mascota.getNombre());//lo seteamos geteando(ingresando) el nuevo nombre
        mascotaBD.setEspecie(mascota.getEspecie());
        mascotaBD.setRaza(mascota.getRaza());
        mascotaBD.setColor(mascota.getColor());
        mascotaRepository.save(mascota);//guardamos el objeto actualizado
    }

    @Override
    public void deleteMascota(Long id_mascota) {
        mascotaRepository.deleteById(id_mascota);
    }

    @Override
    public List<FichaDTO> obtenerFichas() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        List<FichaDTO> fichasDTO = new ArrayList<>();

        for (Mascota mascota : mascotas) {
            Duenio duenio = mascota.getDuenio();
            FichaDTO fichaDTO = new FichaDTO(
                    mascota.getId_mascota(),
                    mascota.getNombre(),
                    mascota.getEspecie(),
                    mascota.getRaza(),
                    mascota.getColor(),
                    duenio.getNombre_duenio(),
                    duenio.getApellido(),
                    duenio.getCelular(),
                    duenio.getDni());
            fichasDTO.add(fichaDTO);
        }
        return fichasDTO;
    }



}
