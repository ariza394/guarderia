package com.imagen.service.imagenservice.dto.mascotaDto;

import com.imagen.service.imagenservice.dto.BaseSaveDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaSaveDto extends BaseSaveDto{

    private Boolean publica;

    private Long idMascota;
}
