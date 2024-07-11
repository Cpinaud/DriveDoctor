package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioDiagnostico {

    Diagnostico buscar(Integer diagnostico);
    void guardar(Diagnostico diagnostico);

    Diagnostico findById(Integer idDiagnostico);
    List<Diagnostico> findAll();

    List<Diagnostico> obtenerPorSintomasIds(List<Integer> idSintomas);
    Diagnostico obtenerPorSintomaId(Integer idSintoma);
}
