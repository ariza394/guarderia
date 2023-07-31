package com.imagen.service.imagenservice.services;

import java.util.List;

import com.imagen.service.imagenservice.dto.BaseGetOneImageDto;
import com.imagen.service.imagenservice.dto.mascotaDto.MascotaGetDto;
import com.imagen.service.imagenservice.dto.mascotaDto.MascotaSaveDto;

public interface MascotaService {
    MascotaGetDto save(MascotaSaveDto mascotaDto);
    BaseGetOneImageDto savePerfil(MascotaSaveDto mascotaDto);
    BaseGetOneImageDto findOneImage(Long id);
    List<BaseGetOneImageDto> findImagesByMascotaId(Long id); 
}
