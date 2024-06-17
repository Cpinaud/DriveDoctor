package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioItemTablero {

    List<ItemTablero> obtenerTodosLosItems();
    ItemTablero findById(Integer idItemTablero);
}
