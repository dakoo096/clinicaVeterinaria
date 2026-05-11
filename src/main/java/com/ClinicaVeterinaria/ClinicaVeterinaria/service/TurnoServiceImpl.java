package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Atencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Turno;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.AtencionRepository;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private AtencionRepository atencionRepository;

    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public List<Turno> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return turnoRepository.findByFechaHoraInicioBetween(start, end);
    }

    @Override
    public Optional<Turno> findById(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public Turno save(Turno turno) {
        if (turno.getEstado() == null) {
            turno.setEstado("PENDIENTE");
        }
        return turnoRepository.save(turno);
    }

    @Override
    public void delete(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Long confirmarAsistencia(Long idTurno) {
        Turno turno = turnoRepository.findById(idTurno)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        turno.setEstado("PRESENTE");
        turnoRepository.save(turno);

        // Crear registro de atención inicial
        Atencion atencion = new Atencion();
        atencion.setFecha(LocalDateTime.now());
        atencion.setTurno(turno);
        atencion.setUsuario(turno.getUsuario());
        
        // Tomamos la primera mascota si existe
        if (turno.getMascotas() != null && !turno.getMascotas().isEmpty()) {
            Mascota mascota = turno.getMascotas().iterator().next();
            atencion.setMascota(mascota);
        }

        Atencion savedAtencion = atencionRepository.save(atencion);
        return savedAtencion.getId_atencion();
    }
}
