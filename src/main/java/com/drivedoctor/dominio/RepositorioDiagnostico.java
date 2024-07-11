package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioDiagnostico extends RepositoryCRUD<Diagnostico> {

    Diagnostico buscar(Integer diagnostico);

    Diagnostico findById(Integer idDiagnostico);
    List<Diagnostico> findAll();


    List<Diagnostico> obtenerPorSintomasIds(List<Integer> idSintomas);
    Diagnostico obtenerPorSintomaId(Integer idSintoma);
}
