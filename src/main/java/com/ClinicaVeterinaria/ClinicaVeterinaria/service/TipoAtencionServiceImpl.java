package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.TipoAtencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.TipoAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAtencionServiceImpl implements TipoAtencionService {

    @Autowired
    private TipoAtencionRepository tipoAtencionRepository;

    @Override
    public List<TipoAtencion> findAll() {
        return tipoAtencionRepository.findAll();
    }

    @Override
    public Optional<TipoAtencion> findById(Long id) {
        return tipoAtencionRepository.findById(id);
    }

    @Override
    public TipoAtencion save(TipoAtencion tipoAtencion) {
        return tipoAtencionRepository.save(tipoAtencion);
    }

    @Override
    public void update(Long id, TipoAtencion tipoAtencion) {
        tipoAtencionRepository.findById(id).ifPresent(db -> {
            db.setNombre(tipoAtencion.getNombre());
            db.setDescripcion(tipoAtencion.getDescripcion());
            db.setDuracionMinutos(tipoAtencion.getDuracionMinutos());
            db.setPrecio(tipoAtencion.getPrecio());
            db.setCantidad(tipoAtencion.getCantidad());
            tipoAtencionRepository.save(db);
        });
    }

    @Override
    public void delete(Long id) {
        tipoAtencionRepository.deleteById(id);
    }
}
