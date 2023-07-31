package com.ciao.macotaservice.services;

import java.util.List;

import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;
import com.ciao.macotaservice.dto.razaDto.RazaSavedDto;

public interface RazaService {
    RazaBaseDto save(RazaSavedDto razaSavedDto);
    List<RazaBaseDto> findAll();
    RazaBaseDto findById(Long id);
    RazaBaseDto update(RazaSavedDto razaSavedDto);
}
