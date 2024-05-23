package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioDiagnostico {

    void guardarDiagnostico(Diagnostico diagnostico);
    Diagnostico findById(Integer idDiagnostico);
    double calcularRiesgoPorSintoma(List<Sintoma> sintomas);
}
