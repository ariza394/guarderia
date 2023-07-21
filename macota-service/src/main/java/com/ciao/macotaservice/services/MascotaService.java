package com.ciao.macotaservice.services;

import java.util.List;

import com.ciao.macotaservice.dto.mascotaDto.MascotaApiGetIdDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseGetDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaSaveDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotasApiByUserDto;

public interface MascotaService {
    List<MascotaApiGetIdDto> findAll();
    
    MascotaApiGetIdDto findById(Long id);

    MascotasApiByUserDto findMascotasByUser(Long id);

    MascotaBaseDto updatePerfilImage(String imagen, Long id);

    MascotaBaseGetDto save(MascotaSaveDto mascotaDto);

    /* MascotaBaseDto update(MascotaSaveDto mascotaDto, Long id);

    Integer remove(Long id); */
}
