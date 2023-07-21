package com.ciao.userservice.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ciao.userservice.dto.userDto.UserDto;
import com.ciao.userservice.dto.userDto.UserGetDto;
import com.ciao.userservice.dto.userDto.UserSavedDto;
import com.ciao.userservice.models.User;
import com.ciao.userservice.repositories.UserRepository;
import com.ciao.userservice.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserGetDto> findAll() {

        Iterable<User> userIterable = repository.findAll();
        List<User> userList = new ArrayList<>();

        userIterable.forEach(userList::add);

        List<UserGetDto> userDtoList = new ArrayList<>();

        for (User user : userList) {
            UserGetDto userDto = modelMapper.map(user, UserGetDto.class);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public UserGetDto findById(Long id) {
        Optional<User> optionalUser =  repository.findById(id);
        User user = optionalUser.get();

        return modelMapper.map(user, UserGetDto.class);
    }

    @Override
    @Transactional
    public UserDto save(UserSavedDto userSaveDto) {
        User user = modelMapper.map(userSaveDto, User.class);
        User savedUser = repository.save(user);

        UserDto userDto = modelMapper.map(savedUser, UserDto.class);
        return userDto;
    }

    @Override
    @Transactional
    public Integer remove(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            repository.delete(user);
            
            return 1; // Usuario eliminado exitosamente
        } else {
            return 0; // No se encontró el usuario, no se pudo eliminar
        }
    }

    @Override
    @Transactional
    public UserGetDto update(UserGetDto user, Long id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (user.getNombre() != null) {
            existingUser.setNombre(user.getNombre());
        }
        if (user.getApellido() != null) {
            existingUser.setApellido(user.getApellido());
        }
    
        User updatedUser = repository.save(existingUser);
        return modelMapper.map(updatedUser, UserGetDto.class);
    }
    
}
