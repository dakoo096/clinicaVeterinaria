package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.TipoAtencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.TipoAtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-atenciones")
@CrossOrigin(origins = "http://localhost:5173")
public class TipoAtencionController {

    @Autowired
    private TipoAtencionService tipoAtencionService;

    @GetMapping
    public ResponseEntity<List<TipoAtencion>> getAll() {
        return ResponseEntity.ok(tipoAtencionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAtencion> getById(@PathVariable Long id) {
        return tipoAtencionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<TipoAtencion> create(@RequestBody TipoAtencion tipoAtencion) {
        return ResponseEntity.ok(tipoAtencionService.save(tipoAtencion));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TipoAtencion tipoAtencion) {
        tipoAtencionService.update(id, tipoAtencion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoAtencionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
