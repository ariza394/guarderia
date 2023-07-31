package com.ciao.macotaservice.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ciao.macotaservice.dto.mascotaDto.MascotaApiGetIdDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseGetDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaSaveDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotasApiByUserDto;

public interface MascotaService {
    List<MascotasApiByUserDto> findAll();
    
    MascotaApiGetIdDto findById(Long id);

    MascotasApiByUserDto findMascotasByUser(Long id);

    MascotaBaseDto updatePerfilImage(Long imagen, Long id);

    MascotaBaseGetDto save(MascotaSaveDto mascotaDto);

    /* MascotaBaseDto update(MascotaSaveDto mascotaDto, Long id);

    Integer remove(Long id); */
}
