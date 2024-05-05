package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioSintoma {

    Sintoma buscar(String problema);
    void guaradar(Sintoma sintoma);
}
