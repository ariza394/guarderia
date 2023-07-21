package com.imagen.service.imagenservice.dto.razaDto;

import com.imagen.service.imagenservice.dto.BaseSaveDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RazaSaveDto extends BaseSaveDto{
    private Long idRaza;
}
