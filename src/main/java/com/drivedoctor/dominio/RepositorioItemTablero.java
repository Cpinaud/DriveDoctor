package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioItemTablero extends Busqueda<ItemTablero,Integer>,Guardado<ItemTablero>  {

    List<ItemTablero> findAll();
}
