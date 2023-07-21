package com.ciao.macotaservice.controllers;

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

import com.ciao.macotaservice.dto.mascotaDto.MascotaBaseDto;
import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;
import com.ciao.macotaservice.dto.razaDto.RazaSavedDto;
import com.ciao.macotaservice.services.RazaService;

@RestController
@RequestMapping("/razas")
public class RazaController {

    @Autowired
    private RazaService service;

    @PostMapping
    public ResponseEntity<RazaBaseDto> create(@ModelAttribute RazaSavedDto razaSavedDto) {
        RazaBaseDto response = service.save(razaSavedDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
