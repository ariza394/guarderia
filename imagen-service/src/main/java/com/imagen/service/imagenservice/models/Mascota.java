package com.imagen.service.imagenservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "imagenes_mascota")
public class Mascota extends Base{

    @Column(nullable = false)
    private Boolean publica;

    @Column(nullable = false)
    private Long idMascota;
}
