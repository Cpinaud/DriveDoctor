package com.drivedoctor.dominio;

import javax.transaction.Transactional;

@Transactional
public interface RepositorioParteDelAuto {

    void guardar(ParteDelAuto parteDelAuto);
    ParteDelAuto obtenerParte(Integer idParteDelAuto);

}
