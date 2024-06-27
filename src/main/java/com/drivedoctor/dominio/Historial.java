package com.drivedoctor.dominio;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToMany
    @JoinTable(
            name = "historial_sintoma",
            joinColumns = @JoinColumn(name = "historial_id"),
            inverseJoinColumns = @JoinColumn(name = "sintoma_id")
    )
    private List<Sintoma> sintomas;

    @ManyToMany
    @JoinTable(
            name = "historial_diagnostico",
            joinColumns = @JoinColumn(name = "historial_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnostico_id")
    )
    private List<Diagnostico> diagnosticos;

   @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Historial() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
}
