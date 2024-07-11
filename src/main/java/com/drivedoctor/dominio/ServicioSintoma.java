package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;


import com.drivedoctor.dominio.excepcion.SintomaExistente;


import java.util.List;

public interface ServicioSintoma {

    List<Sintoma> problemaEnTablero(ItemTablero itemTablero);

    void guardarSintoma(Sintoma sintoma) throws DiagnosticoNotFoundException, SintomaExistente;

    List<Sintoma> findAll();


    List<Sintoma> problemasEnTablero(List<ItemTablero> items);
    List<Sintoma> obtenerSintomasPorSuId(List<Integer> sintomas);

    Sintoma findById (Integer id) throws ElementoNoEncontrado;


}
