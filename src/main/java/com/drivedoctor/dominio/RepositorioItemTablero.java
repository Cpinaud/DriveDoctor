package com.drivedoctor.dominio;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioItemTablero extends RepositoryCRUD<ItemTablero> {

    List<ItemTablero> findAll();
}
