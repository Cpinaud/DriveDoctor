package com.drivedoctor.dominio;

import java.util.List;

public interface ServiceTaller {

    List<Taller> findAll();
    List<Taller> obtenerTalleresPorLocalidad(String localidad);
}
