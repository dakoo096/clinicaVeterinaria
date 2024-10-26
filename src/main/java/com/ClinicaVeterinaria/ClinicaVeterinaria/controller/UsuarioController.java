
package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Usuario;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/registrar")
    public String registrarPage(){
        return "registro";
    }
    
    @PostMapping("/registro")
    public String registro(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.crearUsuario(usuario);
        return "redirect:/login";
    }
    
    @GetMapping(value={"login","/"})
    public String loginPage(){
        return "login";
    }
    
    @GetMapping("/access")
    public String access(HttpSession session) {
        Object userId = session.getAttribute("user_session_id");
        if (userId != null) {
            Optional<Usuario> optionalUser = usuarioService.findUsuarioById(Long.parseLong(userId.toString()));
            if (optionalUser.isPresent()) {
                session.setAttribute("user_session_id", optionalUser.get().getId_usuario());
                return "redirect:/layout";
            }
        }
        return "redirect:/login";
    }
    
    @GetMapping("/layout")
public String layout(HttpSession session, Model model) {
    Long userId =(Long) session.getAttribute("user_session_id"); //accedemos al id del usuario
    if(userId != null){ //si no es null
        Optional<Usuario> optionalUser = usuarioService.findUsuarioById(userId); //lo buscamos por el id en el service
        if(optionalUser.isPresent()){//si esta presente
            model.addAttribute("usuario",optionalUser.get());//mandamos el modelo a la vista
        }
    }
    return "header"; // Asegúrate de que este archivo existe en resources/templates
} 

@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
