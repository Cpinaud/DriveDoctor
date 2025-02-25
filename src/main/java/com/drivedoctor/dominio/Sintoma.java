package com.drivedoctor.dominio;


import javax.persistence.*;
import java.util.List;

@Entity
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSintoma;
    private String nombre;
    private String descripcion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_tablero_id")
    private ItemTablero itemTablero;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diagnostico")
    private Diagnostico diagnostico;

    @ManyToMany(mappedBy = "sintomas")
    private List<Historial> historiales;

    public Sintoma() {
    }

    public Sintoma(ItemTablero itemTablero) {
        this.itemTablero = itemTablero;

    }

    public List<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(List<Historial> historiales) {
        this.historiales = historiales;
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

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setId(Integer i) {
        this.idSintoma=i;
    }
}


