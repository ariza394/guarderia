package com.ciao.macotaservice.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciao.macotaservice.dto.imagenOneDto.ImagenOneDto;
import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;
import com.ciao.macotaservice.dto.razaDto.RazaSavedDto;
import com.ciao.macotaservice.dto.userDto.UserDto;
import com.ciao.macotaservice.models.Raza;
import com.ciao.macotaservice.repositories.MascotaRepository;
import com.ciao.macotaservice.repositories.RazaRepository;
import com.ciao.macotaservice.services.RazaService;

import lombok.AllArgsConstructor;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class RazaServiceImpl implements RazaService{

    @Autowired
    private RazaRepository repository;
    private WebClient webClient;
    private ModelMapper modelMapper;

    @Override
    public RazaBaseDto save(RazaSavedDto razaSavedDto) {
        
        Raza raza = modelMapper.map(razaSavedDto, Raza.class);
        raza = repository.save(raza);

        MultipartFile archivosAdjuntos = razaSavedDto.getImagenPerfil();
        Long idRaza = raza.getId();

        // Crea un MultiValueMap para el cuerpo del formulario
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("archivosAdjuntos", archivosAdjuntos.getResource());
        formData.add("idRaza", idRaza);

        ImagenOneDto imagenRazaDto = webClient.post().uri("http://localhost:8082/imagenes/raza/perfil")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(formData))
        .retrieve()
        .bodyToMono(ImagenOneDto.class)
        .block();

        raza.setImagenId(imagenRazaDto.getId());
        repository.save(raza);

        RazaBaseDto razaBaseDto = modelMapper.map(raza, RazaBaseDto.class);
        razaBaseDto.setImagen(imagenRazaDto.getImagen());
        return razaBaseDto;
    }

    @Override
    public RazaBaseDto findById(Long id) {
        Raza raza = repository.findById(id).get();
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
