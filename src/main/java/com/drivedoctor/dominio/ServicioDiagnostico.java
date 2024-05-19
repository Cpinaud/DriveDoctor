package com.drivedoctor.dominio;

public interface ServicioDiagnostico {

    void guardarDiagnostico(Diagnostico diagnostico);
    Diagnostico findById(Integer idDiagnostico);
}
