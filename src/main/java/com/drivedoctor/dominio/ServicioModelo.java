package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ModeloNoEncontrado;

import java.util.List;

public interface ServicioModelo {

    List<Modelo> obtenerModeloPorMarca(Marca marca);

    Modelo getById(Integer modeloId) throws ModeloNoEncontrado;
}
