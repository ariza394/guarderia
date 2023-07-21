package com.ciao.macotaservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ciao.macotaservice.dto.mascotaDto.MascotaApiGetIdDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseGetDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotaSaveDto;
import com.ciao.macotaservice.dto.mascotaDto.MascotasApiByUserDto;
import com.ciao.macotaservice.services.MascotaService;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService service;

    @GetMapping
    public List<MascotaApiGetIdDto> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaApiGetIdDto> show(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all/user/{id}")
    public ResponseEntity<MascotasApiByUserDto> showMascotas(@PathVariable Long id) {
        return new ResponseEntity<>(service.findMascotasByUser(id), HttpStatus.OK);
    }

    @PostMapping("/update/perfil/{id}")
    public ResponseEntity<MascotaBaseDto> updatePerfil(
    @RequestParam("imagen") String imagen,
    @PathVariable Long id
    ) {
        MascotaBaseDto response = service.updatePerfilImage(imagen, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MascotaBaseGetDto> create(@ModelAttribute MascotaSaveDto mascotaSaveDto) {
        MascotaBaseGetDto mascotaBaseGetDto = service.save(mascotaSaveDto);
        return new ResponseEntity<>(mascotaBaseGetDto, HttpStatus.OK);
    }
}
