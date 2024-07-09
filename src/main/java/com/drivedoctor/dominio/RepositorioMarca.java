package com.drivedoctor.dominio;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface RepositorioMarca extends RepositoryCRUD<Marca> {
    List<Marca> getAll();

}
