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
    private ItemTablero itemTablero;

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

    public ItemTablero getItemTablero() {
        return itemTablero;
    }

    public void setItemTablero(ItemTablero itemTablero) {
        this.itemTablero = itemTablero;
    }
}
