package com.imagen.service.imagenservice.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagen.service.imagenservice.dto.BaseGetOneImageDto;
import com.imagen.service.imagenservice.dto.razaDto.RazaSaveDto;
import com.imagen.service.imagenservice.models.Raza;
import com.imagen.service.imagenservice.repositories.RazaRepository;
import com.imagen.service.imagenservice.services.RazaService;
import com.imagen.service.imagenservice.services.StorageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RazaServiceImpl implements RazaService{

    @Autowired
    private RazaRepository repository;
    private ModelMapper modelMapper;
    

    @Autowired
    private StorageService serviceStorage;


    @Override
    public BaseGetOneImageDto savePerfil(RazaSaveDto razaSaveDto) {
        Raza raza = modelMapper.map(razaSaveDto, Raza.class);
        MultipartFile imagenPerfil = razaSaveDto.getArchivosAdjuntos().get(0);

        String filePath = serviceStorage.uploadFile(imagenPerfil, "razas");
        raza.setImagen(filePath);
        raza = repository.save(raza);

        BaseGetOneImageDto baseGetOneImageDto = new BaseGetOneImageDto();
        baseGetOneImageDto.setId(raza.getId());
        baseGetOneImageDto.setImagen(filePath);
        System.out.println("imagen--------->" + baseGetOneImageDto.getImagen());
        return baseGetOneImageDto;
    }


    @Override
    public BaseGetOneImageDto update(RazaSaveDto razaSaveDto) {
        Optional <Raza> Optionalraza = repository.findById(razaSaveDto.getId());
        
        if(Optionalraza.isPresent()){
            Raza razaDb = Optionalraza.get();
            
            //elimina imagen
            serviceStorage.deleteFile(razaDb.getImagen());
            
            MultipartFile imagenPerfil = razaSaveDto.getArchivosAdjuntos().get(0);
            String filePath = serviceStorage.uploadFile(imagenPerfil, "razas");

            razaDb.setIdRaza(razaSaveDto.getIdRaza());
            razaDb.setImagen(filePath);
            repository.save(razaDb);


            BaseGetOneImageDto baseGetOneImageDto = modelMapper.map(razaDb, BaseGetOneImageDto.class);
            return baseGetOneImageDto;
        }
        else {
            return null;
        }

    }


    @Override
    public BaseGetOneImageDto findOneImage(Long id) {
        Raza raza = repository.findById(id).get();
        BaseGetOneImageDto oneImage = modelMapper.map(raza,BaseGetOneImageDto.class);
        return oneImage;
    }
    
}
