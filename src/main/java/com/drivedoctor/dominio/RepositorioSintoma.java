package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioSintoma extends RepositoryCRUD<Sintoma> {

    Sintoma buscar(ItemTablero itemTablero);
    List<Sintoma> obtenerPorItemTablero(ItemTablero itemTablero);
    List<Sintoma> getAll();

    Sintoma findByName(String nombre);

    List<Sintoma> obtenerPorItemsTablero(List<ItemTablero> items);
    List<Sintoma> obtenerLosSintomasPorSusIds(List<Integer> idsSintomas);


}
