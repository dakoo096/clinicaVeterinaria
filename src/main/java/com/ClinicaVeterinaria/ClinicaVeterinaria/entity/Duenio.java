    package com.ClinicaVeterinaria.ClinicaVeterinaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Duenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_duenio;
    private String dni;
    private String nombre_duenio;
    private String apellido;
    private String celular;
    
    @OneToMany(mappedBy="duenio")//colocamos el nombre del objeto con el que asociamos la relacion en la otra clase
    private List<Mascota> listaMascotas;
}
