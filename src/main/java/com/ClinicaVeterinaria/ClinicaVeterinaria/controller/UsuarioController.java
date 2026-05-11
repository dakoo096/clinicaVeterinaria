package com.ClinicaVeterinaria.ClinicaVeterinaria.controller;

import com.ClinicaVeterinaria.ClinicaVeterinaria.dto.LoginRequest;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Usuario;
import com.ClinicaVeterinaria.ClinicaVeterinaria.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            
            HttpSession session = request.getSession(true);
            
            Optional<Usuario> usuario = usuarioService.findUsuarioByUsername(loginRequest.getUsername());
            if (usuario.isPresent()) {
                session.setAttribute("user_session_id", usuario.get().getId_usuario());
                return ResponseEntity.ok(usuario.get());
            }
            
            return ResponseEntity.status(401).body("Usuario no encontrado tras autenticación");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas: " + e.getMessage());
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registro(@RequestBody Usuario usuario) {
        usuario.setPassword_hash(passwordEncoder.encode(usuario.getPassword_hash()));
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("user_session_id");
        if (userId != null) {
            return usuarioService.findUsuarioById(userId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(401).build());
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Sesión cerrada");
    }
}
