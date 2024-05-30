package com.drivedoctor.dominio;
import java.util.List;
public interface RepositorioMarca {
    List<Marca> getAll();

    Marca getById(Integer idMarca);


}
