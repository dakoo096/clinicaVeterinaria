/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.FichaDTO;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface FichaService {


    public FichaDTO buscarPorNombreDeMascota(String nombre);
}
