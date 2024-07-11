package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

import java.util.List;

public interface ServicioHistorial {
    void agregarHistoria(Integer idVehiculo, List<Integer> idSintoma, List<Integer>  idDiagnostico) throws ElementoNoEncontrado;
}
