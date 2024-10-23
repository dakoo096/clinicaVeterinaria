    
package com.ClinicaVeterinaria.ClinicaVeterinaria.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mascota;
    private String nombre;
    private String especie;
    private String raza;
    private String color;
    
    @ManyToOne
    @JoinColumn(name="idDuenio")
    private Duenio duenio;
    
    @OneToMany(mappedBy = "mascota",cascade = CascadeType.ALL) //creamos la relacion bidireccional
    private List<Atencion> listaAtenciones;
}
