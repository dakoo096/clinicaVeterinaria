package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Raza;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/razas")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @GetMapping("/traer")
    public List<Raza> traerRazas() {
        return razaService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Raza> saveRaza(@RequestBody Raza raza) {
        return ResponseEntity.ok(razaService.save(raza));
    }

    @DeleteMapping("/borrar/{id_raza}")
    public ResponseEntity<String> deleteRaza(@PathVariable Long id_raza) {
        razaService.deleteById(id_raza);
        return ResponseEntity.ok("Raza eliminada correctamente");
    }

    @GetMapping("/traer/{id_raza}")
    public ResponseEntity<Raza> traerRaza(@PathVariable Long id_raza) {
        return razaService.findById(id_raza)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id_raza}")
    public ResponseEntity<Raza> editRaza(@PathVariable Long id_raza, @RequestBody Raza raza) {
        Raza updated = razaService.update(id_raza, raza);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
