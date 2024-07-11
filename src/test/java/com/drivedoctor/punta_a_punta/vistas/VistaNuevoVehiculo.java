package com.drivedoctor.punta_a_punta.vistas;

import com.microsoft.playwright.Page;

public class VistaNuevoVehiculo extends VistaWeb {
        public VistaNuevoVehiculo(Page page) {
            super(page);
            page.navigate("http://localhost:8080/drivedoctor/nuevo-vehiculo");
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

    public void seleccionarOpcion(String elemento,String value) {
        page.selectOption(elemento,value);
    }

    public void rellenarInput(String input, String valor){
        page.fill(input, valor);

    }
}
