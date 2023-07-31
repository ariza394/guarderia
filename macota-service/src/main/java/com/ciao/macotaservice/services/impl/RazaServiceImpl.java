package com.ciao.macotaservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ciao.macotaservice.dto.imagenOneDto.ImagenOneDto;
import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;
import com.ciao.macotaservice.dto.razaDto.RazaSavedDto;
import com.ciao.macotaservice.models.Raza;
import com.ciao.macotaservice.repositories.RazaRepository;
import com.ciao.macotaservice.services.RazaService;

import lombok.AllArgsConstructor;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.ciao.macotaservice.utilities.GetOneImage;
import com.ciao.macotaservice.utilities.RestApiClient;

@AllArgsConstructor
@Service
public class RazaServiceImpl implements RazaService{

    private final RazaRepository repository;
    private final ModelMapper modelMapper;
    private final GetOneImage getOneImage;
    private final RestApiClient restApiClientImages;

    @Autowired
    public RazaServiceImpl(RazaRepository repository, ModelMapper modelMapper, @Qualifier("restApiClientImages") RestApiClient restApiClientImages, GetOneImage getOneImage) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.restApiClientImages = restApiClientImages;
        this.getOneImage = getOneImage;
    }

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

        ImagenOneDto imagenRazaDto = restApiClientImages.post("/imagenes/raza/perfil", formData, ImagenOneDto.class);


        raza.setImagenId(imagenRazaDto.getId());
        repository.save(raza);

        RazaBaseDto razaBaseDto = modelMapper.map(raza, RazaBaseDto.class);
        razaBaseDto.setImagen(imagenRazaDto.getImagen());
        return razaBaseDto;
    }

    @Override
    public List<RazaBaseDto> findAll() {
        Iterable<Raza> iterableRazas = repository.findAll();
        List<RazaBaseDto> razas = new ArrayList<>();
        iterableRazas.forEach(raza -> {
            RazaBaseDto razaBaseDto = modelMapper.map(raza, RazaBaseDto.class);
            ImagenOneDto imagen = getOneImage.imagenByID(raza.getImagenId(), "raza");
            razaBaseDto.setImagen(imagen.getImagen());
            razas.add(razaBaseDto);
        });

        return razas;
    }

    @Override
    public RazaBaseDto findById(Long id) {
        Raza raza = repository.findById(id).get();

        RazaBaseDto razaBaseDto = modelMapper.map(raza, RazaBaseDto.class);
        ImagenOneDto imagen = getOneImage.imagenByID(raza.getImagenId(), "raza");
        razaBaseDto.setImagen(imagen.getImagen());

        return razaBaseDto;
    }

    @Override
    public RazaBaseDto update(RazaSavedDto razaSavedDto) {
        Long id = razaSavedDto.getId();
        Optional<Raza> optionalRaza = repository.findById(id);

        if (optionalRaza.isPresent()) { 
            Raza razaInDB = optionalRaza.get();

            Raza raza = modelMapper.map(razaSavedDto, Raza.class);

            MultipartFile archivosAdjuntos = razaSavedDto.getImagenPerfil();
            Long idRaza = raza.getId();

            // Crea un MultiValueMap para el cuerpo del formulario
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            formData.add("archivosAdjuntos", archivosAdjuntos.getResource());
            formData.add("idRaza", idRaza);
            formData.add("id", razaSavedDto.getImagenId());
            
            ImagenOneDto imagen = getOneImage.imagenUpdateByID("raza", formData);

            razaInDB.setImagenId(imagen.getId());
            razaInDB.setRaza(razaSavedDto.getRaza());
            razaInDB.setSubRaza(razaSavedDto.getSubRaza());
            razaInDB = repository.save(razaInDB);

            RazaBaseDto razaBaseDto = modelMapper.map(razaInDB, RazaBaseDto.class);

            return razaBaseDto;
        }
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    
}
