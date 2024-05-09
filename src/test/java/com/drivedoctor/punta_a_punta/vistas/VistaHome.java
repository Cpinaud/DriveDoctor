package com.drivedoctor.punta_a_punta.vistas;

import com.microsoft.playwright.Page;

public class VistaHome extends VistaWeb{

    public VistaHome(Page page) {
        super(page);
        page.navigate("localhost:8080/spring/home");
    }

        public void irASintoma() { this.darClickEnElElemento("#irSintoma");}



}
