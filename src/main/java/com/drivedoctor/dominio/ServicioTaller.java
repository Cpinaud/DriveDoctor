package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioTaller {

    List<Taller> findAll();
    List<Taller> obtenerTalleresPorLocalidad(String localidad);
}
