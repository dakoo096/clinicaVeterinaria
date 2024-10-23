/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ClinicaVeterinaria.ClinicaVeterinaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_atencion;
    private String titulo;
    @DateTimeFormat(pattern = "dd-MM-yyyy'  'HH:mm:ss") // Ajustamos el formato de la fecha
    private LocalDateTime fecha_atencion;
    private String detalle_atencion;

    @ManyToOne
    @JoinColumn(name = "id_mascota")//va a ser la fk para mascota
    private Mascota mascota;

}
