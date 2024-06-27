package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioHistorial {
    void agregarHistoria(Integer idVehiculo, List<Integer> idSintoma, List<Integer>  idDiagnostico);
}
