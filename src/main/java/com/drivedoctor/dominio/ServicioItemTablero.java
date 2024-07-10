package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;

import java.util.List;

public interface ServicioItemTablero {

    List<ItemTablero> obtenerTodosLosItems() ;

    ItemTablero findById(Integer idItemTablero) throws ItemNoEncontrado;
    void guardarItemTablero(ItemTablero itemTablero);

}
