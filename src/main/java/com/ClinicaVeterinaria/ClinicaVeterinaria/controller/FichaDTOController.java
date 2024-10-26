package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Usuario;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.DuenioService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.FichaService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.MascotaService;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private UsuarioService usuarioService;

    //creamos el metodo para poder traer la sesion actual a cada endpoint 
    private void agregarUsuarioAlModelo(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user_session_id");
        if (userId != null) {
            Optional<Usuario> optionalUser = usuarioService.findUsuarioById(userId);
            optionalUser.ifPresent(usuario -> model.addAttribute("usuario", usuario));
        }
    }

    @GetMapping("/fichas/traer")
    public String listarFichas(HttpSession session, Model model) {
        agregarUsuarioAlModelo(session, model);//traemos el metodo para agregar la sesion
        //obtenemos las fichas desde mascotaService
        List<FichaDTO> listaFichas = mascotaService.obtenerFichas();
        model.addAttribute("fichas", listaFichas);
        return "listarFichas";

    }

    //buscar mascota por nombre en listarFichas
    @GetMapping("/buscarFichaPorNombre")
    public String buscarFichaPorNombre(HttpSession session, @RequestParam("nombre") String nombre, Model model) {
        agregarUsuarioAlModelo(session, model);//traemos el metodo para agregar la sesion

// Buscar la ficha a través del DTO
        FichaDTO ficha = fichaService.buscarPorNombreDeMascota(nombre);

        if (ficha == null) {
            model.addAttribute("mensaje", "Mascota no encontrada");
            return "listarFichas";
        } else {
            // Agregar la ficha al modelo
            model.addAttribute("ficha", ficha);
        }
        return "detalleFicha"; // Redirige a la vista que mostrará los detalles de la ficha
    }

}
