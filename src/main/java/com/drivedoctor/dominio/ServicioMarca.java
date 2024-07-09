package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

import java.util.List;

public interface ServicioMarca {
    List<Marca> obtenerMarcasAll();
    Marca findById (Integer id) throws ElementoNoEncontrado;




}
