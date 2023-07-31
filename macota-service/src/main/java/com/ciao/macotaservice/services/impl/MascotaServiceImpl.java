package com.ciao.macotaservice.services.impl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.ciao.macotaservice.utilities.RestApiClient;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MascotaServiceImpl implements MascotaService{
    
    @Autowired
    private MascotaRepository repository;
    private RazaRepository razaRepository;
    private WebClient webClient;
    private ModelMapper modelMapper;
    private final RestApiClient restApiClientImages;
    private final RestApiClient restApiClientUser;


    public MascotaServiceImpl(MascotaRepository repository, RazaRepository razaRepository, WebClient webClient,
            ModelMapper modelMapper,@Qualifier("restApiClientImages") RestApiClient restApiClientImages, 
             @Qualifier("restApiClientUsers") RestApiClient restApiClientUser) {
        this.repository = repository;
        this.razaRepository = razaRepository;
        this.webClient = webClient;
        this.modelMapper = modelMapper;
        this.restApiClientImages = restApiClientImages;
        this.restApiClientUser = restApiClientUser;
    }





    @Override
    public MascotaApiGetIdDto findById(Long id) {
        Mascota mascota = repository.findById(id).get();

        UserDto userDto = webClient.get().uri("http://localhost:8080/users/" + mascota.getUserId())
        .retrieve()
        .bodyToMono(UserDto.class)
        .block();
        

        
        //objto Mascota
        MascotaBaseGetDto mascotaBaseGetDto = modelMapper.map(mascota,MascotaBaseGetDto.class);
        
        //setea imagen
        ImagenOneDto imagenOneDto = restApiClientImages.get("/imagenes/one/" + mascota.getImagenPerfilId() + "/mascota", ImagenOneDto.class);

        mascotaBaseGetDto.setImagenPerfil(imagenOneDto.getImagen());
        
        // setea raza
        RazaBaseDto razaBaseDto = modelMapper.map(mascota.getIdRaza(), RazaBaseDto.class);
        mascotaBaseGetDto.setRaza(razaBaseDto);


        MascotaApiGetIdDto mascotaApiGetIdDto = new MascotaApiGetIdDto();
        mascotaApiGetIdDto.setMascota(mascotaBaseGetDto);
        mascotaApiGetIdDto.setUser(userDto);

        
        mascotaApiGetIdDto.setUser(userDto);

        return mascotaApiGetIdDto;  
    }

    @Override
    public List<MascotasApiByUserDto> findAll() {

        // traemos todos los usuarios
        List<UserDto> usersDto = webClient.get().uri("http://localhost:8080/users")
        .retrieve()
        .bodyToFlux(UserDto.class)
        .collectList()
        .block();

        //iniciamos objeto de devolucion
        List<MascotasApiByUserDto> allMascotasByUser = new ArrayList<>();

        usersDto.forEach(user -> {
            MascotasApiByUserDto mascotasApiByUserDto = new MascotasApiByUserDto();

            // Obtener todas las mascotas del usuario con el id especificado
            List<Mascota> mascotas = repository.findAllByUserId(user.getId());

            //se guardan las mascotas
            List<MascotaBaseDto> mascotasDto = new ArrayList<>();

            // Mapear las mascotas a DTO
            for (Mascota mascota : mascotas) {
                MascotaBaseDto mascotaDto = modelMapper.map(mascota, MascotaBaseDto.class);
                mascotasDto.add(mascotaDto);
            }

            // seteamos el objeto mascotasApiByUserDto para agregarlo al array
            mascotasApiByUserDto.setMascota(mascotasDto);
            mascotasApiByUserDto.setUser(user);

            allMascotasByUser.add(mascotasApiByUserDto);
        });

        return allMascotasByUser;
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
    public MascotaBaseDto updatePerfilImage(Long imagen, Long id) {
        Mascota mascota = repository.findById(id).get();
        mascota.setImagenPerfilId(imagen);
        repository.save(mascota);
        MascotaBaseDto mascotaBaseDto = modelMapper.map(mascota,MascotaBaseDto.class);
        return mascotaBaseDto;
    }

    @Override
    public MascotaBaseGetDto save(MascotaSaveDto mascotaSaveDto) {
        Mascota mascota = modelMapper.map(mascotaSaveDto, Mascota.class);
        repository.save(mascota);
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
