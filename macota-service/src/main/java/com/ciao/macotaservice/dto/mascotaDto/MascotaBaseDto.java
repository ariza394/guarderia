package com.ciao.macotaservice.dto.mascotaDto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaBaseDto {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String cuidados;
    private String info;
}
