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
public class UserGetDto extends UserDto{
    private LocalDate fecha_registro;
}
