package com.ciao.macotaservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ciao.macotaservice.models.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long>{
    List<Mascota> findAllByUserId(Long userId);
}
