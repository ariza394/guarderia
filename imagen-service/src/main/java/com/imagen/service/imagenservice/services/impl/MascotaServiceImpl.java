package com.imagen.service.imagenservice.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imagen.service.imagenservice.dto.BaseGetOneImageDto;
import com.imagen.service.imagenservice.dto.mascotaDto.MascotaGetDto;
import com.imagen.service.imagenservice.dto.mascotaDto.MascotaSaveDto;
import com.imagen.service.imagenservice.models.Mascota;
import com.imagen.service.imagenservice.repositories.MascotaRepository;
import com.imagen.service.imagenservice.services.MascotaService;
import com.imagen.service.imagenservice.services.StorageService;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MascotaServiceImpl implements MascotaService{
    
    @Autowired
    private MascotaRepository repository;
    private ModelMapper modelMapper;
    

    @Autowired
    private StorageService serviceStorage;

    public MascotaGetDto save(MascotaSaveDto mascotaSaveDto) {
        Mascota mascota = modelMapper.map(mascotaSaveDto, Mascota.class);
        List<String> filePaths = new ArrayList<>();
        for (MultipartFile archivo : mascotaSaveDto.getArchivosAdjuntos()) {
            try {   
                    String filePath = serviceStorage.uploadFile(archivo, "mascotas");
                    Mascota mascotaToSaved = new Mascota();
                    mascotaToSaved.setImagen(filePath);
                    mascotaToSaved.setIdMascota(mascota.getIdMascota());
                    mascotaToSaved.setPublica(mascota.getPublica());
                    filePaths.add(filePath);
                    repository.save(mascotaToSaved);
                } catch (Exception e) {
                    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }
        MascotaGetDto mascotaReturnDto = new MascotaGetDto();
        mascotaReturnDto.setId(mascota.getId());
        mascotaReturnDto.setIdMascota(mascota.getIdMascota());
        mascotaReturnDto.setPublica(mascota.getPublica());
        mascotaReturnDto.setImagenes(filePaths);
        return mascotaReturnDto;
    }

    

    @Override
    public BaseGetOneImageDto savePerfil(MascotaSaveDto mascotaSaveDto) {
        Mascota mascota = modelMapper.map(mascotaSaveDto, Mascota.class);
        MultipartFile imagenPerfil = mascotaSaveDto.getArchivosAdjuntos().get(0);
        String filePath = serviceStorage.uploadFile(imagenPerfil, "mascotas");
        mascota.setImagen(filePath);
        mascota = repository.save(mascota);

        BaseGetOneImageDto mascotaReturnDto = new BaseGetOneImageDto();
        mascotaReturnDto.setId(mascota.getId());
        mascotaReturnDto.setImagen(mascota.getImagen());
        return mascotaReturnDto;
    }

    @Override
    public List<BaseGetOneImageDto> findImagesByMascotaId(Long id) {
        List<Mascota> imagenesMascota = repository.findAllByIdMascota(id);
        List<BaseGetOneImageDto> mascotaReturnDto = new ArrayList<>();

        imagenesMascota.forEach(mascota ->{

            BaseGetOneImageDto oneMascota = new BaseGetOneImageDto();
            oneMascota.setId(mascota.getId());
            oneMascota.setImagen(mascota.getImagen());
            mascotaReturnDto.add(oneMascota);
        });

        return mascotaReturnDto;
    }
    
}
