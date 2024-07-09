package com.drivedoctor.dominio;

import java.util.List;

public interface RepositorioSintoma {

    Sintoma buscar(ItemTablero itemTablero);
    void guardar(Sintoma sintoma);
    List<Sintoma> obtenerPorItemTablero(ItemTablero itemTablero);
    List<Sintoma> getAll();
    Sintoma findByName(String nombre);
    Sintoma findById(Integer idSintoma);
    List<Sintoma> obtenerPorItemsTablero(List<ItemTablero> items);
    List<Sintoma> obtenerPorIds(List<Integer> idSintomas);
    List<Sintoma> obtenerLosSintomasPorSusIds(List<Integer> idsSintomas);


}
