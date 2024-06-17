package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioItemTablero {

    List<ItemTablero> findAll();
    ItemTablero findById(Integer idItemTablero);
    void guardar(ItemTablero itemTablero);
}
