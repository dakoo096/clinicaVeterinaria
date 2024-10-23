
package com.ClinicaVeterinaria.ClinicaVeterinaria.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaDTO implements Serializable{  
    private Long id_mascota;
    private String nombre;
    private String especie;
    private String raza;
    private String color;
    private String nombre_duenio;
    private String apellido_duenio;
    private String celular;
    private String dni;
    
}
