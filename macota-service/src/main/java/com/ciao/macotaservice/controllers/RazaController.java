package com.ciao.macotaservice.controllers;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<RazaBaseDto>> list() {
        List<RazaBaseDto> response = service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazaBaseDto> show(@PathVariable Long id){
        RazaBaseDto response = service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RazaBaseDto> create(@ModelAttribute RazaSavedDto razaSavedDto) {
        RazaBaseDto response = service.save(razaSavedDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<RazaBaseDto> update(@ModelAttribute RazaSavedDto razaSavedDto) {
        RazaBaseDto response = service.update(razaSavedDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
