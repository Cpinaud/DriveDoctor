package com.drivedoctor.dominio.excepcion;

public class ItemsNoEncontrados extends Throwable {
    public ItemsNoEncontrados() {
        super("ITEMS NO ES ENCONTRADOS");
    }
}
