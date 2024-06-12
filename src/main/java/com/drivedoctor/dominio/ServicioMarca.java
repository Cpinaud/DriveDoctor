package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioMarca {
    List<Marca> obtenerMarcasAll();

    Marca obtenerMarcaPorId(Integer idMarca);


}
