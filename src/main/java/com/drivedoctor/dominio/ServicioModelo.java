package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioModelo {

    List<Modelo> obtenerModeloPorMarca(Marca marca);

    Modelo getById(Integer modeloId);
}
