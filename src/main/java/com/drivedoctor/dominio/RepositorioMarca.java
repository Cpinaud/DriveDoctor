package com.drivedoctor.dominio;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface RepositorioMarca extends Busqueda<Marca,Integer> {
    List<Marca> getAll();

}
