package com.imagen.service.imagenservice.services;

import com.imagen.service.imagenservice.dto.BaseGetOneImageDto;
import com.imagen.service.imagenservice.dto.razaDto.RazaSaveDto;

public interface RazaService {
    BaseGetOneImageDto savePerfil(RazaSaveDto razaSaveDto);
    BaseGetOneImageDto findOneImage(Long id);
    BaseGetOneImageDto update(RazaSaveDto razaSaveDto);
}
