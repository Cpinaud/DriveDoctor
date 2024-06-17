package com.drivedoctor.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdDiagnostico;
    private String descripcion;


    @OneToMany(mappedBy = "diagnostico")
    private List<Sintoma> sintomas;

    public Diagnostico(List<Sintoma> sintomas) {
        this.sintomas = sintomas;

    }

    public Diagnostico() {
    }

    public Integer getIdDiagnostico() {
        return IdDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        IdDiagnostico = idDiagnostico;
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
}


