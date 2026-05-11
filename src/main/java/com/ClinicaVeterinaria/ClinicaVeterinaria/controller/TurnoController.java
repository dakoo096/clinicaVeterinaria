package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Turno;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "http://localhost:5173")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<Turno>> getAllTurnos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        
        if (start != null && end != null) {
            return ResponseEntity.ok(turnoService.findByDateRange(start, end));
        }
        return ResponseEntity.ok(turnoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable Long id) {
        return turnoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Turno> createTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.save(turno));
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<Long> confirmarTurno(@PathVariable Long id) {
        Long idAtencion = turnoService.confirmarAsistencia(id);
        return ResponseEntity.ok(idAtencion);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        turnoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
