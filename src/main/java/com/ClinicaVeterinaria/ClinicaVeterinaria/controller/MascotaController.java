package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.DuenioService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.MascotaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MascotaController {

    //inyectamos el servicio
    @Autowired
    private MascotaService mascotaService;

    @Autowired
    DuenioService duenioService;

    //traemos las mascotas al html listarClientes
    @GetMapping("/mascotas/traer")
    public String traerMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.findAllMascotas());
        return "listarMascotas";
    }

    //endpoint que tomamos desde el nav y redirige al html correspondiente
    @GetMapping("/mascotas/nuevaMascota")
    public String registrarNuevaMascota(Model model) {
        model.addAttribute("duenios", duenioService.findAllDuenios());
        //creamos un objeto mascota vacio para  el form
        model.addAttribute("mascota", new Mascota());
        return "/registrarMascota";
    }

    //guardamos la mascota
    @PostMapping("/mascotas/crear")
    public String saveCliente(@ModelAttribute Mascota mascota) {
        mascotaService.saveMascota(mascota);
        return "redirect:/mascotas/traer";
    }

    //borramos la mascota
    @GetMapping("/mascotas/borrar/{id_mascota}")
    public String deleteMascota(@PathVariable Long id_mascota) {
        Mascota mascota = mascotaService.findMascotaById(id_mascota).get();
        mascotaService.deleteMascota(id_mascota);
        return "redirect:/mascotas/traer";
    }

    //traemos la mascota a editar
    @GetMapping("/mascotas/traerEditar/{id_mascota}")
    public String traerEditarMascota(@PathVariable Long id_mascota, Model model) {
        //obtenemos la mascota buscandola por el id
        Mascota mascota = mascotaService.findMascotaById(id_mascota).get();
        model.addAttribute("duenios", duenioService.findAllDuenios());
        //creamos un objeto mascota vacio para  el form
        model.addAttribute("mascota", mascota);
        return "editarMascota";
    }

    //editamos la macota en el formulario con los datos cargamos
    @PostMapping("/mascotas/editar/{id_mascota}")
    public String editMascota(@PathVariable Long id_mascota, @ModelAttribute Mascota mascota) {
        mascotaService.findMascotaById(id_mascota);
        mascotaService.updateMascota(id_mascota, mascota);
        return "redirect:/mascotas/traer";
    }

    

}
