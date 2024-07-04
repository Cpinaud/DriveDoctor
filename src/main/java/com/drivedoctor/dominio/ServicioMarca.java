package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioMarca extends Busqueda<Marca,Integer> {
    List<Marca> obtenerMarcasAll();




}
