package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioModelo extends Busqueda<Modelo,Integer> {

    List<Modelo> getAll();

    List<Modelo> getByMarca(Marca marca);


}
