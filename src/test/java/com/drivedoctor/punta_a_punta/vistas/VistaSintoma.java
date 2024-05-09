package com.drivedoctor.punta_a_punta.vistas;

import com.microsoft.playwright.Page;

public class VistaSintoma  extends VistaWeb {

    public VistaSintoma(Page page) {
        super(page);
        page.navigate("localhost:8080/spring/sintoma");
    }


}
