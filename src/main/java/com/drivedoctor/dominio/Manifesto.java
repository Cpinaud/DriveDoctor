package com.drivedoctor.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Manifesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idManifesto;
    private String descripcion;


    public Integer getIdManifesto() {
        return idManifesto;
    }

    public void setIdManifesto(Integer idManifesto) {
        this.idManifesto = idManifesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
