package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.MarcaNoEncontrada;

import java.util.List;

public interface ServicioMarca {
    List<Marca> obtenerMarcasAll();

    Marca obtenerMarcaPorId(Integer idMarca) throws MarcaNoEncontrada;


}
