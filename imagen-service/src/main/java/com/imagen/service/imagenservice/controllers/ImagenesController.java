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
import com.imagen.service.imagenservice.services.MascotaService;
import com.imagen.service.imagenservice.services.RazaService;

@RestController
@RequestMapping("/imagenes")
public class ImagenesController {

    @Autowired
    private MascotaService mascotaImageService;

    @Autowired
    private RazaService razaImageService;
    
    @GetMapping("/one/{id}/{servicio}")
    public ResponseEntity<BaseGetOneImageDto> findOneById(@PathVariable Long id, @PathVariable String servicio) {
        BaseGetOneImageDto imageReturn = new BaseGetOneImageDto();
        if("raza".equals(servicio)){
            imageReturn = razaImageService.findOneImage(id);
        }
        return new ResponseEntity<>(imageReturn, HttpStatus.OK);
    }
}
