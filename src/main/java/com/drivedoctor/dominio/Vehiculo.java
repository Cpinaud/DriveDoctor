package com.drivedoctor.dominio;

import javax.persistence.*;

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Marca marca;
    private Modelo modelo;
    private int anoFabricacion;

    @Column(unique = true)
    private String patente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    public Vehiculo() {

    }

    public Vehiculo(Marca marca, Modelo modelo,int anoFabricacion,String patente) {
        this.marca=marca;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
        this.patente = patente;
    }


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }


    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getAnoFabricacion() {
        return this.anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public String getPatente() {
        return this.patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
}
