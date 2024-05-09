package com.drivedoctor.dominio;


import java.util.List;

public interface ServicioSintoma {

    List<Sintoma> problemaEnTablero(ItemTablero itemTablero);
    void guardarSintoma(Sintoma sintoma);



}
