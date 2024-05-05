package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioSintoma {

    Sintoma buscar(ItemTablero itemTablero);
    void guardar(Sintoma sintoma);
    List<Sintoma> obtenerPorItemTablero(ItemTablero itemTablero);
}
