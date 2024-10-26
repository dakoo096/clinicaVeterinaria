package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = usuarioService.findUsuarioByUsername(username);

        if (optionalUser.isPresent()) {
            session.setAttribute("user_session_id", optionalUser.get().getId_usuario());
            Usuario usuario = optionalUser.get();
            return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword())
                    .roles()
                    .build();
        } else {
            throw new UsernameNotFoundException("¡Usuario no encontrado!");
        }
    }
}
