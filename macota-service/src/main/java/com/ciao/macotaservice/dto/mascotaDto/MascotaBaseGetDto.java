package com.ciao.macotaservice.dto.mascotaDto;

import com.ciao.macotaservice.dto.razaDto.RazaBaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaBaseGetDto extends MascotaBaseDto{
    private RazaBaseDto raza;
    private String imagenPerfil;
}
