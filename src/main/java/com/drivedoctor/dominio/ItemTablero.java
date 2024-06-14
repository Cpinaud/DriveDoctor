package com.drivedoctor.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class ItemTablero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemTablero;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "ItemTablero")
    private List<Sintoma> sintomas;

    public ItemTablero(String nombre){
        this.nombre = nombre;
    }

    public ItemTablero(){

    }

    public Integer getIdItemTablero() {
        return idItemTablero;
    }

    public void setIdItemTablero(Integer idItemTablero) {
        this.idItemTablero = idItemTablero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
