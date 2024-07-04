package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioDiagnostico extends Busqueda<Diagnostico,Integer>,Guardado<Diagnostico> {

    Diagnostico buscar(Integer diagnostico);

    List<Diagnostico> findByIds(List<Integer> idsDiagnostico);

    List<Diagnostico> obtenerPorSintomasIds(List<Integer> idSintomas);
    Diagnostico obtenerPorSintomaId(Integer idSintoma);
}
