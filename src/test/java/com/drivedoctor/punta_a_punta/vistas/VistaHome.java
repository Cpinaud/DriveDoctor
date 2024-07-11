package com.drivedoctor.punta_a_punta.vistas;

import com.microsoft.playwright.Page;

public class VistaHome extends VistaWeb{

    public VistaHome(Page page) {
        super(page);
        page.navigate("http://localhost:8080/drivedoctor/home");
    }

    public String obtenerTextoDeElemento(String selectorCSS) {
        System.out.println(page.content());
        page.locator(selectorCSS).waitFor();
        return this.obtenerTextoDelElemento(selectorCSS);
    }
    public void darClickEnUnElemento(String elemento) {
        System.out.println(page.content());
        this.darClickEnElElemento(elemento);
    }

}
