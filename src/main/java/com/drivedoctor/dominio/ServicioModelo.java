package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

import java.util.List;

public interface ServicioModelo {

    List<Modelo> obtenerModeloPorMarca(Marca marca);

    Modelo findById (Integer id) throws ElementoNoEncontrado;


}
