package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Duenio;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.DuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DuenioController {

    //inyectamos el servicio
    @Autowired
    private DuenioService duenioService;

    @GetMapping("/")
    public String home(Model model) {
        return "/layout";
    }

    //traemos a los dueños al html listarDuenios
    @GetMapping("/duenios/traer")
    public String traerDuenios(Model model){
        model.addAttribute("duenios",duenioService.findAllDuenios());
        return "listarDuenios";
    }
    
    //tomamos desde el nav y lo redirige a la vista correspondiente
    @GetMapping("/duenios/nuevoDuenio")
    public String registrarNuevoDuenio(){
        return "registrarDuenio";
    }
    
    //guardamos el dueño
    @PostMapping("/duenios/crear")
    public String saveDuenio(@ModelAttribute Duenio duenio){
        duenioService.saveDuenio(duenio);
        return "redirect:/duenios/traer";
    }
    
    //borrar al dueño
    @GetMapping("/duenios/borrar/{id_duenio}")
    public String deleteDuenio(@PathVariable Long id_duenio){
        //obtenemos el duenio por id
        Duenio duenio = duenioService.findDuenioById(id_duenio).get();//le pasamos la id y lo traermo
        duenioService.deleteDuenio(id_duenio);
        return "redirect:/duenios/traer";
    }
    
    //editar al dueño,primero lo traemos a la vista
    @GetMapping("/duenios/traerEditar/{id_duenio}")
    public String traerEditarDuenio(@PathVariable Long id_duenio,Model model){
        //obtenemos el duenio por id y lo traemos\
        Duenio duenio = duenioService.findDuenioById(id_duenio).get();
        model.addAttribute("duenio",duenio);
        return "editarDuenio";
    }
        //editamos en el formulario y guardamos los datos nuevos
        @PostMapping("/duenios/editar/{id_duenio}")
        public String editDuenio(@PathVariable Long id_duenio,@ModelAttribute Duenio duenio){
        duenioService.findDuenioById(id_duenio);
        duenioService.updateDuenio(id_duenio, duenio);
        return "redirect:/duenios/traer";
    }
    
    
}
