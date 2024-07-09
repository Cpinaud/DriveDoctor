package com.drivedoctor.dominio;


import com.drivedoctor.dominio.excepcion.DiagnosticoInvalido;
import com.drivedoctor.dominio.excepcion.SintomaExistente;

import java.util.List;

public interface ServicioSintoma {

    List<Sintoma> problemaEnTablero(ItemTablero itemTablero);
    void guardarSintoma(Sintoma sintoma) throws DiagnosticoInvalido, SintomaExistente;
    List<Sintoma> findAll();
    Sintoma findById(Integer idSintoma);
    List<Sintoma> problemasEnTablero(List<ItemTablero> items);
    List<Sintoma> obtenerSintomasPorSuId(List<Integer> sintomas);


}
