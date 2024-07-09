package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

import java.util.List;

public interface ServicioItemTablero {

    List<ItemTablero> obtenerTodosLosItems() ;

    ItemTablero findById (Integer id) throws ElementoNoEncontrado;
}
