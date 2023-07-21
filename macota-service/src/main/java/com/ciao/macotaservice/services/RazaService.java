package com.ciao.macotaservice.services;

import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;
import com.ciao.macotaservice.dto.razaDto.RazaSavedDto;

public interface RazaService {
    RazaBaseDto save(RazaSavedDto razaSavedDto);
    RazaBaseDto findById(Long id);
}
