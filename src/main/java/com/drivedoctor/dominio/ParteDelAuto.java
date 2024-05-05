package com.drivedoctor.dominio;

import javax.persistence.*;

@Entity
public class ParteDelAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParteDelAuto;
    private String descripcion;


    public Integer getIdParteDelAuto() {
        return idParteDelAuto;
    }

    public void setIdParteDelAuto(Integer idParteDelAuto) {
        this.idParteDelAuto = idParteDelAuto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
