
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Duenio;
import java.util.List;
import java.util.Optional;


public interface DuenioService {
    
    //buscar todos los dueños
    List<Duenio> findAllDuenios();
    
    //buscamos duenio por id
    Optional<Duenio> findDuenioById(Long id_duenio);
    
    //guardamos un dueño
    Duenio saveDuenio(Duenio duenio);
    
    //actualizar
    void updateDuenio(Long id_duenio,Duenio duenio);
    
    void deleteDuenio(Long id_duenio);
}
