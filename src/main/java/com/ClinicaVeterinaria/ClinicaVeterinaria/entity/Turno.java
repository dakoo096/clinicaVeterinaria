package com.ClinicaVeterinaria.ClinicaVeterinaria.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    @JsonProperty("id_turno")
    private Long idTurno;

    @Column(name = "fecha_hora_inicio", nullable = false)
    @JsonProperty("fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin", nullable = false)
    @JsonProperty("fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("listaMascotas")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_tipo_atencion", nullable = false)
    private TipoAtencion tipoAtencion;

    @ManyToMany
    @JoinTable(
        name = "turno_mascota",
        joinColumns = @JoinColumn(name = "id_turno"),
        inverseJoinColumns = @JoinColumn(name = "id_mascota")
    )
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"persona", "listaAtenciones"})
    private Set<Mascota> mascotas;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "turno")
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"turno", "persona", "mascota"})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Atencion> atenciones;
}
