package com.drivedoctor.dominio.excepcion;

public class DiagnosticoNotFoundException extends RuntimeException {
    public DiagnosticoNotFoundException(String message) {
        super(message);
    }
}
