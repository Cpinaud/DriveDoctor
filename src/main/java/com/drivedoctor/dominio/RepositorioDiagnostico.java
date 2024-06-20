package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioDiagnostico {

    Diagnostico buscar(Integer diagnostico);
    void guardar(Diagnostico diagnostico);

    Diagnostico findById(Integer idDiagnostico);
    List<Diagnostico> findByIds(List<Integer> idsDiagnostico);

    List<Diagnostico> obtenerPorSintomasIds(List<Integer> idSintomas);
    Diagnostico obtenerPorSintomaId(Integer idSintoma);
}
