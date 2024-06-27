package com.drivedoctor.punta_a_punta.vistas;

import com.microsoft.playwright.Page;

public class VistaSintoma  extends VistaWeb {

    public VistaSintoma(Page page) {
        super(page);
        page.navigate("http://localhost:8080/drivedoctor/sintoma");
    }


    public String obtenerTextoDeElemento(String elemento) {
        return this.obtenerTextoDelElemento(elemento);
    }
    public void darClickEnUnElemento(String elemento) {
        this.darClickEnElElemento(elemento);
    }


}
