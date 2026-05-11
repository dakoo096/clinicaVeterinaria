package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Especie;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especies")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping("/traer")
    public List<Especie> traerEspecies() {
        return especieService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Especie> saveEspecie(@RequestBody Especie especie) {
        return ResponseEntity.ok(especieService.save(especie));
    }

    @DeleteMapping("/borrar/{id_especie}")
    public ResponseEntity<String> deleteEspecie(@PathVariable Long id_especie) {
        especieService.deleteById(id_especie);
        return ResponseEntity.ok("Especie eliminada correctamente");
    }

    @GetMapping("/traer/{id_especie}")
    public ResponseEntity<Especie> traerEspecie(@PathVariable Long id_especie) {
        return especieService.findById(id_especie)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id_especie}")
    public ResponseEntity<Especie> editEspecie(@PathVariable Long id_especie, @RequestBody Especie especie) {
        Especie updated = especieService.update(id_especie, especie);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
