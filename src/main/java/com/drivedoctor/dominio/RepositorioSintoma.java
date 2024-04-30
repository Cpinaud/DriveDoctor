package com.drivedoctor.dominio;

public interface RepositorioSintoma {

    Sintoma buscar(String problema);
    void guaradar(Sintoma sintoma);
}
