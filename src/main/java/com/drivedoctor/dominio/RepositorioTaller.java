package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioTaller {

    List<Taller> findAll();
    List<Taller> obtenerPorLocalidad(String localidad);
}
