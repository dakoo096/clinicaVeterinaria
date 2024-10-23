
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import java.util.List;
import java.util.Optional;


public interface MascotaService {
    //en service creamos los metodos que vamos a utilizar
    
    //buscar todas las mascotas
    List<Mascota> findAllMascotas();
    
    //buscar una mascota por id
    Optional<Mascota> findMascotaById(Long id_mascota);
    
    //guardamos una mascota
    Mascota saveMascota(Mascota mascota);
    
    //actualizar
    void updateMascota(Long id_mascota,Mascota mascota);
    
    //eliminar
    void deleteMascota(Long id_mascota);
    
    public List<FichaDTO> obtenerFichas();



}
