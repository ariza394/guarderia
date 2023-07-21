package com.ciao.macotaservice.dto.razaDto;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RazaSavedDto extends RazaBaseDto{
    private MultipartFile imagenPerfil;
}
