/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Usuario;
import java.util.Optional;


public interface UsuarioService {
    
    Usuario crearUsuario(Usuario usuario);
    
    Optional<Usuario> findUsuarioById(Long id_usuario);
    
    Optional<Usuario> findUsuarioByUsername(String username);
}
