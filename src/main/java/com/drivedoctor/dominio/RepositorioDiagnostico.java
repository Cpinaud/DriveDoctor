package com.drivedoctor.dominio;

import java.util.Optional;

public interface RepositorioDiagnostico {

    Diagnostico buscar(Integer diagnostico);
    void guardar(Diagnostico diagnostico);

    Diagnostico findById(Integer idDiagnostico);
}
