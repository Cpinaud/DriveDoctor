package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioItemTablero extends Busqueda<ItemTablero,Integer> {

    List<ItemTablero> obtenerTodosLosItems() ;
}
