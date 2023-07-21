package com.ciao.macotaservice.dto.mascotaDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaSaveDto extends MascotaBaseDto{
    private Long userId;
    private Boolean imagenPublica;
    private Long idRaza;
}
