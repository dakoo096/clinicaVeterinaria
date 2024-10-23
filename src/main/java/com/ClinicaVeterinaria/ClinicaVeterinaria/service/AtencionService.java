
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Atencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import java.util.List;
import java.util.Optional;


public interface AtencionService {
    //buscar todas las atenciones
    List<Atencion> findAllAtencionesByMascota(Long id_mascota);
    

    //buscar atencion por id
    Optional<Atencion> findAtencionById(Long id_atencion);
    
    //guardar la atencion
    Atencion saveAtencion(Atencion atencion);
    
    //actualizar atencion
    void updateAtencion(Long id_atencion,Atencion atencion);
    
    //borrar atencion
    void deleteAtencion(Long id_atencion);
    
}
