package com.ciao.macotaservice.models;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "mascotas")
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Lob
    private String cuidados;

    @Lob
    private String info;

    @NotEmpty
    @Column(nullable = false)
    private Boolean imagenPublica;

    private Long imagenPerfilId;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "id_Raza")
    private Raza idRaza;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCuidados(String cuidados) {
        this.cuidados = cuidados;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setImagenPublica(Boolean imagenPublica) {
        this.imagenPublica = imagenPublica;
    }

    public void setImagenPerfilId(Long imagenPerfilId) {
        this.imagenPerfilId = imagenPerfilId;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setIdRaza(Raza idRaza) {
        this.idRaza = idRaza;
    }

    
}
