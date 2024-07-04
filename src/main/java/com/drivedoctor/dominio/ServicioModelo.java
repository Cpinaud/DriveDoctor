package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioModelo extends Busqueda<Modelo,Integer> {

    List<Modelo> obtenerModeloPorMarca(Marca marca);


}
