package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioSintoma extends Busqueda<Sintoma,Integer>,Guardado<Sintoma> {

    Sintoma buscar(ItemTablero itemTablero);
    List<Sintoma> obtenerPorItemTablero(ItemTablero itemTablero);
    List<Sintoma> getAll();
    List<Sintoma> obtenerPorItemsTablero(List<ItemTablero> items);
    List<Sintoma> obtenerLosSintomasPorSusIds(List<Integer> idsSintomas);


}
