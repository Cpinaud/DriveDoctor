package com.drivedoctor.dominio;

import javax.transaction.Transactional;

@Transactional
public interface RepositorioHistorial extends Busqueda<Historial,Integer>,Guardado<Historial> {

}
