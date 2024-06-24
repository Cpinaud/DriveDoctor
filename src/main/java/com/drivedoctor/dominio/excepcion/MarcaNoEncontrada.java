package com.drivedoctor.dominio.excepcion;

import com.drivedoctor.dominio.Marca;

public class MarcaNoEncontrada extends Exception {
    public MarcaNoEncontrada() {
        super("Marca no encontrada");
    }
}
