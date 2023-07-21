package com.imagen.service.imagenservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagen.service.imagenservice.dto.BaseGetOneImageDto;
import com.imagen.service.imagenservice.dto.razaDto.RazaSaveDto;
import com.imagen.service.imagenservice.services.RazaService;

@RestController
@RequestMapping("/imagenes/raza")
public class RazaController {

    @Autowired
    private RazaService service;

    @PostMapping("/perfil")
    public ResponseEntity<BaseGetOneImageDto> createImagePerfil(@ModelAttribute RazaSaveDto raza) {

        BaseGetOneImageDto razaSaved = service.savePerfil(raza);
        return new ResponseEntity<>(razaSaved, HttpStatus.CREATED);
    }
    
}
