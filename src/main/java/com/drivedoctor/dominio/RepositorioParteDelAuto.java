package com.drivedoctor.dominio;

public interface RepositorioParteDelAuto {

    void guardar(ParteDelAuto parteDelAuto);
    ParteDelAuto obtenerParte(Integer idParteDelAuto);

}
