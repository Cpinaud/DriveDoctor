package com.drivedoctor.dominio.excepcion;

public class DemasiadosSintomas extends RuntimeException {
    public DemasiadosSintomas(String message) {
        super(message);
    }
}
