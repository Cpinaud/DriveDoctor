package com.drivedoctor.dominio.excepcion;

public class ModeloNoEncontrado extends Exception {
    public ModeloNoEncontrado() {
        super("Modelo No Encontrado");
    }
}
