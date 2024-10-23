package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Atencion;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.AtencionService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.MascotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AtencionController {

    //inyectamos el servicio
    @Autowired
    private AtencionService atencionService;

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/atenciones/traer/{id_mascota}")
    public String traerAtencionesPormascota(@PathVariable Long id_mascota, Model model) {
        List<Atencion> atenciones = atencionService.findAllAtencionesByMascota(id_mascota);
        model.addAttribute("atenciones", atenciones);
        Mascota mascota = mascotaService.findMascotaById(id_mascota).get();
        model.addAttribute("mascota", mascota);
        return "listarAtenciones";
    }

    //tomamos desde el boton agregar atencion y redirige a la vista corespondiente
    @GetMapping("/atenciones/nuevaAtencion/{id_mascota}")//le pasamos el id de la mascota para que se vinculen las atenciones solo a esa mascota
    public String registrarNuevaAtencion(@PathVariable Long id_mascota, Model model) {
        Mascota mascota = mascotaService.findMascotaById(id_mascota).get();
        model.addAttribute("mascota", mascota);
        return "registrarAtencion";
    }

    //guardamos una atencion
    @PostMapping("/atenciones/crear")
    public String saveAtencion(@ModelAttribute Atencion atencion) {
        atencionService.saveAtencion(atencion);
        return "redirect:/";
    }

    @GetMapping("/atenciones/traerEditar/{id_atencion}")
    public String traerEditarAtencion(@PathVariable Long id_atencion,Model model){
       Atencion atencion = atencionService.findAtencionById(id_atencion).get();
         if (atencion != null) {
        Mascota mascota = atencion.getMascota(); // Asegúrate de que la mascota esté cargada
        model.addAttribute("atencion", atencion);
        model.addAttribute("mascota", mascota); // Pasar la mascota asociada al modelo
    }
       return "editarAtencion";
    }
    
    @PostMapping("/atenciones/editar/{id_atencion}")
    public String editAtencion(@PathVariable Long id_atencion,@ModelAttribute Atencion atencion){
        atencionService.findAtencionById(id_atencion);
        atencionService.updateAtencion(id_atencion, atencion);
        return "redirect:/";
    }
    
    
    @GetMapping("/atenciones/borrar/{id_atencion}")
    public String deleteAtencion(@PathVariable Long id_atencion){
        Atencion atencion = atencionService.findAtencionById(id_atencion).get();
        atencionService.deleteAtencion(id_atencion);
        return "redirect:/";
    }
    
    
    //endpoint para imprimir todas las evoluciones
    @GetMapping("/atenciones/imprimir/{id_mascota}")
    public String imprimirEvoluciones(@PathVariable Long id_mascota,Model model){
  List<Atencion> atenciones = atencionService.findAllAtencionesByMascota(id_mascota);
        model.addAttribute("atenciones", atenciones);
        Mascota mascota = mascotaService.findMascotaById(id_mascota).get();
        model.addAttribute("mascota", mascota);
        return "imprimirAtenciones";
    }
    
}
