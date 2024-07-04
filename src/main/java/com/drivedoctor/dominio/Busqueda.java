package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

public interface Busqueda<T, Integer> {
    T findById(Integer id) throws ElementoNoEncontrado;



}
