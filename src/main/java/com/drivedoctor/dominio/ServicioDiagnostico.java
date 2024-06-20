package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;

import java.util.List;

public interface ServicioDiagnostico {

    void guardarDiagnostico(Diagnostico diagnostico);
    Diagnostico findById(Integer idDiagnostico);

    //OBTIENE LOS DIAGNOSTICOS POR EL IDs DE MAS SINTOMAS(3), si los sintomas tienen solo 2 items en comun
    List<Diagnostico> findBySintomasIds(List<Integer> isdSintoma);

    double calcularRiesgoPorSintoma(List<Sintoma> sintomas);

    String findDependingId (List<Integer> idsSintoma);
    //DIAGNOSTICO EN RELACION CON EL SINTOMA
    Diagnostico findBySintoma(Sintoma sintoma);
    List<Diagnostico> findBySintomas(List<Sintoma> sintomas);

    Diagnostico findBySintomaId(Integer IdSintoma);

}
