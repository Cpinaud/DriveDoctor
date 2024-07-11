package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioTaller {

    List<Taller> findAll();
    List<Taller> obtenerPorLocalidad(String localidad);
}
