package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioModelo extends RepositoryCRUD<Modelo> {

    List<Modelo> getAll();

    List<Modelo> getByMarca(Marca marca);


}
