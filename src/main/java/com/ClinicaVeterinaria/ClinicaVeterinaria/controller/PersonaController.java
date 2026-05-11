package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Persona;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "http://localhost:5173")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/traer")
    public List<Persona> traerPersonas() {
        return personaService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Persona> savePersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.save(persona));
    }

    @DeleteMapping("/borrar/{id_persona}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id_persona) {
        personaService.deleteById(id_persona);
        return ResponseEntity.ok("Persona eliminada correctamente");
    }

    @GetMapping("/traer/{id_persona}")
    public ResponseEntity<Persona> traerPersona(@PathVariable Long id_persona) {
        return personaService.findById(id_persona)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id_persona}")
    public ResponseEntity<Persona> editPersona(@PathVariable Long id_persona, @RequestBody Persona persona) {
        Persona updated = personaService.update(id_persona, persona);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
