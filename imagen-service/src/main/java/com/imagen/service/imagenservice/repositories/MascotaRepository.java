package com.imagen.service.imagenservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.imagen.service.imagenservice.models.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long>{

    List<Mascota> findAllByIdMascota(Long idMascota);
}
