package com.drivedoctor.dominio;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSintoma;
    private String nombre;
    private String descripcion;
    private ItemTablero itemTablero;

    public Sintoma() {
    }

    public Sintoma(ItemTablero itemTablero) {
        this.itemTablero = itemTablero;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(Integer idSintoma) {
        this.idSintoma = idSintoma;
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


