package com.drivedoctor.dominio;


import java.util.List;

public interface ServicioSintoma {

    List<Sintoma> problemaEnTablero(ItemTablero itemTablero);
    void guardarSintoma(Sintoma sintoma);
    List<Sintoma> findAll();
    Sintoma findById(Integer idSintoma);
    List<Sintoma> problemasEnTablero(List<ItemTablero> items);


}
