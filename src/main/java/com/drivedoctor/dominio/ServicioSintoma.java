package com.drivedoctor.dominio;


import java.util.List;

public interface ServicioSintoma extends Busqueda<Sintoma,Integer>,Guardado<Sintoma>  {

    List<Sintoma> problemaEnTablero(ItemTablero itemTablero);
    List<Sintoma> findAll();
    List<Sintoma> problemasEnTablero(List<ItemTablero> items);
    List<Sintoma> obtenerSintomasPorSuId(List<Integer> sintomas);


}
