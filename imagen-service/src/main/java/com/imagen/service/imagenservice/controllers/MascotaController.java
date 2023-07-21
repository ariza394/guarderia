package com.imagen.service.imagenservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagen.service.imagenservice.dto.BaseGetOneImageDto;
import com.imagen.service.imagenservice.dto.mascotaDto.MascotaGetDto;
import com.imagen.service.imagenservice.dto.mascotaDto.MascotaSaveDto;
import com.imagen.service.imagenservice.services.MascotaService;

@RestController
@RequestMapping("/imagenes/mascota")
public class MascotaController {
    
    @Autowired
    private MascotaService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<BaseGetOneImageDto>> showImagesByIdMascota(@PathVariable Long id) {
        return new ResponseEntity<>(service.findImagesByMascotaId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MascotaGetDto> create(@ModelAttribute MascotaSaveDto mascota) {

        MascotaGetDto mascotaSaved = service.save(mascota);
        return new ResponseEntity<>(mascotaSaved, HttpStatus.CREATED);
    }

    @PostMapping("/perfil")
    public ResponseEntity<BaseGetOneImageDto> createImagePerfil(@ModelAttribute MascotaSaveDto mascota) {

        BaseGetOneImageDto mascotaSaved = service.savePerfil(mascota);
        return new ResponseEntity<>(mascotaSaved, HttpStatus.CREATED);
    }
}
