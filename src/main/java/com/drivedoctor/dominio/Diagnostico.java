package com.drivedoctor.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdDiagnostico;
    private String descripcion;

    @OneToOne(mappedBy = "diagnostico", cascade = CascadeType.ALL)
    private Sintoma sintoma;

    public Diagnostico(Sintoma sintoma) {
        this.sintoma = sintoma;
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

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }
}
