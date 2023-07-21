package com.ciao.macotaservice.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciao.macotaservice.dto.imagenOneDto.ImagenOneDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaApiGetIdDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseGetDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaSaveDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotasApiByUserDto;
import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;
import com.ciao.macotaservice.dto.userDto.UserDto;
import com.ciao.macotaservice.models.Mascota;
import com.ciao.macotaservice.models.Raza;
import com.ciao.macotaservice.repositories.MascotaRepository;
import com.ciao.macotaservice.repositories.RazaRepository;
import com.ciao.macotaservice.services.MascotaService;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class MascotaServiceImpl implements MascotaService{
    
    @Autowired
    private MascotaRepository repository;
    private RazaRepository razaRepository;
    private WebClient webClient;
    private ModelMapper modelMapper;

    @Override
    public MascotaApiGetIdDto findById(Long id) {
        Mascota mascota = repository.findById(id).get();

        UserDto userDto = webClient.get().uri("http://localhost:8080/users/" + mascota.getUserId())
        .retrieve()
        .bodyToMono(UserDto.class)
        .block();
        
        MascotaBaseDto mascotaBaseDto = modelMapper.map(mascota,MascotaBaseDto.class);
        MascotaApiGetIdDto mascotaApiGetIdDto = new MascotaApiGetIdDto();
        mascotaApiGetIdDto.setMascota(mascotaBaseDto);
        mascotaApiGetIdDto.setUser(userDto);

        return mascotaApiGetIdDto;  
    }

    @Override
    public List<MascotaApiGetIdDto> findAll() {
        List<MascotaApiGetIdDto> mascotas = new ArrayList<>();
        repository.findAll().forEach(mascota -> {
            MascotaApiGetIdDto mascotaDto = this.findById(mascota.getId());
            mascotas.add(mascotaDto);
        });
        return mascotas;
    }

    @Override
    public MascotasApiByUserDto findMascotasByUser(Long id) {
        List<MascotaBaseDto> mascotasDto = new ArrayList<>();
        
        // Obtener todas las mascotas del usuario con el id especificado
        List<Mascota> mascotas = repository.findAllByUserId(id);
        
        // Obtener los datos del usuario
        UserDto userDto = webClient.get()
                .uri("http://localhost:8080/users/" + id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
        
        // Mapear las mascotas a DTO
        for (Mascota mascota : mascotas) {
            MascotaBaseDto mascotaDto = modelMapper.map(mascota, MascotaBaseDto.class);
            mascotasDto.add(mascotaDto);
        }
        
        // Crear el objeto MascotasApiByUserDto con los datos del usuario y las mascotas
        MascotasApiByUserDto mascotasByUserDto = new MascotasApiByUserDto();
        mascotasByUserDto.setUser(userDto);
        mascotasByUserDto.setMascota(mascotasDto);
        
        return mascotasByUserDto;
    }

    @Override
    public MascotaBaseDto updatePerfilImage(String imagen, Long id) {
        Mascota mascota = repository.findById(id).get();
        // mascota.se(imagen);
        repository.save(mascota);
        MascotaBaseDto mascotaBaseDto = modelMapper.map(mascota,MascotaBaseDto.class);
        return mascotaBaseDto;
    }

    @Override
    public MascotaBaseGetDto save(MascotaSaveDto mascotaSaveDto) {
        Mascota mascota = modelMapper.map(mascotaSaveDto, Mascota.class);
        repository.save(mascota);
        System.out.println("la razaa de id************");
        Raza raza = razaRepository.findById(mascotaSaveDto.getIdRaza()).get();
        // Obtiene imagen de la mascota
        ImagenOneDto imagen = webClient.get()
                .uri("http://localhost:8082/imagenes/one/" + raza.getImagenId() + "/raza")
                .retrieve()
                .bodyToMono(ImagenOneDto.class)
                .block();
        // RazaBaseDto raza;
        RazaBaseDto razaDto = modelMapper.map(raza, RazaBaseDto.class);
        razaDto.setImagen(imagen.getImagen());

        MascotaBaseGetDto mascotaBaseGetDto = modelMapper.map(mascota, MascotaBaseGetDto.class);
        mascotaBaseGetDto.setRaza(razaDto);
        return mascotaBaseGetDto;
    }

}
