package com.ciao.userservice.dto.userDto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSavedDto extends UserDto{
    private String password;
    private Integer rol;
    private LocalDate fecha_registro;
}
