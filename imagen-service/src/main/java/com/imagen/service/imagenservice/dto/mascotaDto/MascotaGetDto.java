package com.imagen.service.imagenservice.dto.mascotaDto;

import com.imagen.service.imagenservice.dto.BaseGetDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaGetDto extends BaseGetDto{
    private Boolean publica;

    private Long idMascota;
}
