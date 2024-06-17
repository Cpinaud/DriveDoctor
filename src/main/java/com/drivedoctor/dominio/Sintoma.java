package com.drivedoctor.dominio;


import javax.persistence.*;

@Entity
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSintoma;
    private String nombre;
    private String descripcion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_tablero_id")
    private ItemTablero itemTablero;

    @ManyToOne
    @JoinColumn(name = "id_diagnostico")
    private Diagnostico diagnostico;

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

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
}


