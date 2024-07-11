package com.drivedoctor.punta_a_punta;

import com.microsoft.playwright.*;
import com.drivedoctor.punta_a_punta.vistas.VistaSintoma;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class VistaSintomaE2E {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    VistaSintoma vistaSintoma;

    @BeforeAll
    static void abrirNavegador() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

    }

    @AfterAll
    static void cerrarNavegador() {
        playwright.close();
    }

    @BeforeEach
    void crearContextoYPagina() {
        context = browser.newContext();
        Page sintomaPage = context.newPage();
        vistaSintoma = new VistaSintoma(sintomaPage);
    }
    @AfterEach
    void cerrarContexto() {
        context.close();
    }

    @Test
    void deberiaEscribirOpcionesComoTitulo() {
        String titulo = vistaSintoma.obtenerTextoDeElemento("#opciones");

        assertThat("Opciones",equalToIgnoringCase(titulo));
    }

    @Test
    void hacerClickEnSintomaEnTablaroRedigireAMostrarSintomaPorItem() {
        vistaSintoma.darClickEnUnElemento("a.ItemEnTablero");
        String urlNavegada = this.vistaSintoma.obtenerURLActual();
        assertThat(urlNavegada,equalToIgnoringCase("http://localhost:8080/drivedoctor/mostrarSintomaPorItem"));
    }

    @Test
    void hacerClickEnNuevoSintomaRedigireANuevoSintoma() {
        vistaSintoma.darClickEnUnElemento("a.nuevoSintoma");
        String urlNavegada = this.vistaSintoma.obtenerURLActual();
        assertThat(urlNavegada,equalToIgnoringCase("http://localhost:8080/drivedoctor/nuevoSintoma"));
    }

//  

}
