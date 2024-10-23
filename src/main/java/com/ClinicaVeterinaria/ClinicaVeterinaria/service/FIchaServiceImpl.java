/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Mascota;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class FIchaServiceImpl implements FichaService{
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    @Override
    public FichaDTO buscarPorNombreDeMascota(String nombre) {
// Buscar la mascota por nombre
        Mascota mascota = mascotaRepository.findByNombre(nombre);
  // Verificar si la mascota fue encontrada
        if (mascota == null) {
            return null; // Retorna null si no se encuentra
        }
        // Crear el FichaDTO con los datos de la mascota y el dueño
        FichaDTO ficha = new FichaDTO();
        ficha.setId_mascota(mascota.getId_mascota());
        ficha.setNombre(mascota.getNombre());
        ficha.setEspecie(mascota.getEspecie());
        ficha.setRaza(mascota.getRaza());
        ficha.setColor(mascota.getColor());
        ficha.setNombre_duenio(mascota.getDuenio().getNombre_duenio());
        ficha.setApellido_duenio(mascota.getDuenio().getApellido());
        ficha.setCelular(mascota.getDuenio().getCelular());
        ficha.setDni(mascota.getDuenio().getDni());

        return ficha;
    }
    
  
}
