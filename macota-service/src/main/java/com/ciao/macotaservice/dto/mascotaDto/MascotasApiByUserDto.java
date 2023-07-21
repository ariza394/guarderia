package com.ciao.macotaservice.dto.mascotaDto;

import java.util.List;

import com.ciao.macotaservice.dto.userDto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotasApiByUserDto {
    private UserDto user; 
    private List<MascotaBaseDto> mascota; 
}
