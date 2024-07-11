package com.drivedoctor.dominio.excepcion;

public class ItemNoEncontrado extends Throwable {
    public ItemNoEncontrado() {
        super("ITEMS NO ES ENCONTRADOS");
    }
}
