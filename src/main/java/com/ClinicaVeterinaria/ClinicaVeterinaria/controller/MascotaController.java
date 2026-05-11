package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "http://localhost:5173")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/traer")
    public List<Mascota> traerMascotas() {
        return mascotaService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Mascota> saveMascota(@RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.save(mascota));
    }

    @DeleteMapping("/borrar/{id_mascota}")
    public ResponseEntity<String> deleteMascota(@PathVariable Long id_mascota) {
        mascotaService.deleteById(id_mascota);
        return ResponseEntity.ok("Mascota eliminada correctamente");
    }

    @GetMapping("/traer/{id_mascota}")
    public ResponseEntity<Mascota> traerMascota(@PathVariable Long id_mascota) {
        return mascotaService.findById(id_mascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id_mascota}")
    public ResponseEntity<Mascota> editMascota(@PathVariable Long id_mascota, @RequestBody Mascota mascota) {
        Mascota updated = mascotaService.update(id_mascota, mascota);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
