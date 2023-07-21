package com.ciao.userservice.services;

import java.util.List;

import com.ciao.userservice.dto.userDto.UserDto;
import com.ciao.userservice.dto.userDto.UserGetDto;
import com.ciao.userservice.dto.userDto.UserSavedDto;

public interface UserService {
    List<UserGetDto> findAll();
    
    UserGetDto findById(Long id);

    UserDto save(UserSavedDto userDto);

    UserGetDto update(UserGetDto user, Long id);

    Integer remove(Long id);
}
