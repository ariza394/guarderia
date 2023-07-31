package com.ciao.macotaservice.dto.razaDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RazaBaseDto {
    private Long id;
    private String raza;
    private String subRaza;
    private String imagen;
}
