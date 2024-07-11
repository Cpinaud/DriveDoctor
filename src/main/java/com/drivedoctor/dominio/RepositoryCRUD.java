package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;

public interface RepositoryCRUD<T> {
    T findById(Integer id);
    void guardar(T t);

    void modificar (T t);

    void eliminar (T t);



}
