package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Atencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Usuario;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.AtencionService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.MascotaService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
@CrossOrigin(origins = "http://localhost:5173")
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/mascota/{id_mascota}")
    public ResponseEntity<List<Atencion>> traerAtencionesPorMascota(@PathVariable Long id_mascota) {
        return ResponseEntity.ok(atencionService.findAllAtencionesByMascota(id_mascota));
    }

    @PostMapping("/crear")
    public ResponseEntity<Atencion> saveAtencion(@RequestBody Atencion atencion, @AuthenticationPrincipal User user) {
        if (user != null) {
            usuarioService.findUsuarioByUsername(user.getUsername()).ifPresent(atencion::setUsuario);
        }
        return ResponseEntity.ok(atencionService.saveAtencion(atencion));
    }

    @GetMapping("/{id_atencion}")
    public ResponseEntity<Atencion> traerAtencion(@PathVariable Long id_atencion) {
        return atencionService.findAtencionById(id_atencion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id_atencion}")
    public ResponseEntity<String> editAtencion(@PathVariable Long id_atencion, @RequestBody Atencion atencion) {
        atencionService.updateAtencion(id_atencion, atencion);
        return ResponseEntity.ok("Atención actualizada correctamente");
    }

    @DeleteMapping("/borrar/{id_atencion}")
    public ResponseEntity<String> deleteAtencion(@PathVariable Long id_atencion) {
        atencionService.deleteAtencion(id_atencion);
        return ResponseEntity.ok("Atención eliminada correctamente");
    }
}
