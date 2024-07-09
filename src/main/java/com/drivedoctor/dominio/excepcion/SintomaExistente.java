package com.drivedoctor.dominio.excepcion;

public class SintomaExistente extends Exception {
    public SintomaExistente() {
        super("Sintoma ya existe");

    }

}