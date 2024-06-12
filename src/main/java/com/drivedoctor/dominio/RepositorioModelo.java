package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioModelo {

    List<Modelo> getAll();

    List<Modelo> getByMarca(Marca marca);

    Modelo getById(Integer modeloId);
}
