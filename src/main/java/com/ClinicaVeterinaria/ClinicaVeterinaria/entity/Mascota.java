package com.ClinicaVeterinaria.ClinicaVeterinaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mascota;

    @Column(nullable = false, length = 100)
    private String nombre;

    private LocalDate fecha_nacimiento;

    @Column(length = 50)
    private String color;

    private Character sexo;

    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    @JsonIgnoreProperties("listaMascotas")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_raza", nullable = false)
    private Raza raza;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private LocalDateTime deleted_at;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"mascota", "turno"})
    @com.fasterxml.jackson.annotation.JsonProperty("listaAtenciones")
    private List<Atencion> listaAtenciones;
}
