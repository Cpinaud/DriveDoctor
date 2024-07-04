package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.AllItemsEqual;
import com.drivedoctor.dominio.excepcion.DemasiadosItems;
import com.drivedoctor.dominio.excepcion.DemasiadosSintomas;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

import java.util.List;

public interface ServicioDiagnostico extends Busqueda<Diagnostico,Integer> {

    void guardarDiagnostico(Diagnostico diagnostico);

    //OBTIENE LOS DIAGNOSTICOS POR EL IDs DE MAS SINTOMAS(3), si los sintomas tienen solo 2 items en comun
    List<Diagnostico> findBySintomasIds(List<Integer> isdSintoma);

    double calcularRiesgoPorSintoma(List<Sintoma> sintomas);

    List<Diagnostico> findDependingId (List<Integer> idsSintoma) throws AllItemsEqual, DemasiadosSintomas, DemasiadosItems;
    //DIAGNOSTICO EN RELACION CON EL SINTOMA
    Diagnostico findBySintoma(Sintoma sintoma) throws ElementoNoEncontrado;
    List<Diagnostico> findBySintomas(List<Sintoma> sintomas);

    Diagnostico findBySintomaId(Integer IdSintoma);

}
