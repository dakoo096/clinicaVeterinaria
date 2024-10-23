
package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.DuenioService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.FichaService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.MascotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FichaDTOController {
    @Autowired
    private DuenioService duenioService;
    @Autowired
    private MascotaService mascotaService;
    
    @Autowired
    private FichaService fichaService;
    
    @GetMapping("/fichas/traer")
    public String listarFichas(Model model){
      //obtenemos las fichas desde mascotaService
      List<FichaDTO> listaFichas = mascotaService.obtenerFichas();
      model.addAttribute("fichas",listaFichas);
      return "listarFichas";
    
    
    }
    
        //buscar mascota por nombre en listarFichas
  @GetMapping("/buscarFichaPorNombre")
public String buscarFichaPorNombre(@RequestParam("nombre") String nombre, Model model) {
    // Buscar la ficha a través del DTO
    FichaDTO ficha = fichaService.buscarPorNombreDeMascota(nombre);

    if(ficha == null){
        model.addAttribute("mensaje","Mascota no encontrada");
        return "listarFichas";
    }else{
    // Agregar la ficha al modelo
    model.addAttribute("ficha", ficha);
    }
    return "detalleFicha"; // Redirige a la vista que mostrará los detalles de la ficha
}
    
}
