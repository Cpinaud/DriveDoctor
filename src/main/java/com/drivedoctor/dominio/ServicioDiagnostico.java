package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.AllItemsEqual;
import com.drivedoctor.dominio.excepcion.DemasiadosSintomas;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;

import java.util.List;

public interface ServicioDiagnostico {

    void guardarDiagnostico(Diagnostico diagnostico);
    Diagnostico findById(Integer idDiagnostico);


    List<Diagnostico> findAll();
    Sintoma findByName(String name);
    double calcularRiesgoPorSintoma(List<Sintoma> sintomas);

    List<Diagnostico> findDependingId (List<Integer> idsSintoma) throws AllItemsEqual, DemasiadosSintomas;
    //DIAGNOSTICO EN RELACION CON EL SINTOMA
    Diagnostico findBySintoma(Sintoma sintoma);
    List<Diagnostico> findBySintomas(List<Sintoma> sintomas);

    Diagnostico findBySintomaId(Integer IdSintoma);

}
