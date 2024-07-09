package com.drivedoctor.dominio;


import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

import java.util.List;

public interface ServicioSintoma {

    List<Sintoma> problemaEnTablero(ItemTablero itemTablero);
    List<Sintoma> findAll();

    void guardar(Sintoma sintoma);
    List<Sintoma> problemasEnTablero(List<ItemTablero> items);
    List<Sintoma> obtenerSintomasPorSuId(List<Integer> sintomas);

    Sintoma findById (Integer id) throws ElementoNoEncontrado;


}
