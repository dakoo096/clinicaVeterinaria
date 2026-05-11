package com.ClinicaVeterinaria.ClinicaVeterinaria.repository;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.TipoAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtencionRepository extends JpaRepository<TipoAtencion, Long> {
}
