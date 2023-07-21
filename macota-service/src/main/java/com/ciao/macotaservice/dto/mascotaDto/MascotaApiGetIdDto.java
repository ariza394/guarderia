package com.ciao.macotaservice.dto.mascotaDto;

import com.ciao.macotaservice.dto.userDto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaApiGetIdDto {
    private MascotaBaseGetDto mascota;
    private UserDto user;
}
