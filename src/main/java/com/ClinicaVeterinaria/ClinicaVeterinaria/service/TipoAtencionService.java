package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.TipoAtencion;
import java.util.List;
import java.util.Optional;

public interface TipoAtencionService {
    List<TipoAtencion> findAll();
    Optional<TipoAtencion> findById(Long id);
    TipoAtencion save(TipoAtencion tipoAtencion);
    void update(Long id, TipoAtencion tipoAtencion);
    void delete(Long id);
}
