package com.drivedoctor.dominio.excepcion;

public class ElementoNoEncontrado extends Exception {
    public ElementoNoEncontrado() {
    }

    public ElementoNoEncontrado(String message) {
        super(message);
    }
}
