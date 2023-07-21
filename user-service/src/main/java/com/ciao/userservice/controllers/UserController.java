package com.ciao.userservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciao.userservice.dto.userDto.UserDto;
import com.ciao.userservice.dto.userDto.UserGetDto;
import com.ciao.userservice.dto.userDto.UserSavedDto;
import com.ciao.userservice.models.User;
import com.ciao.userservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserGetDto> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> show(@PathVariable Long id) {
        UserGetDto user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@ModelAttribute UserSavedDto user) {
        UserDto userSaved = service.save(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDto> update(@ModelAttribute UserGetDto user, @PathVariable Long id){
        UserGetDto userUpdated = service.update(user, id);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> remove(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.remove(id), HttpStatus.OK);
    }
    
}
