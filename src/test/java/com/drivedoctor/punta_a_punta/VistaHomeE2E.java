package com.drivedoctor.punta_a_punta;

import com.drivedoctor.punta_a_punta.vistas.VistaHome;
import com.drivedoctor.punta_a_punta.vistas.VistaSintoma;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class VistaHomeE2E {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    VistaHome vistaHome;

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
        Page page = context.newPage();
// se crea el contexto en donde el usuario inicia session

        page.navigate("http://localhost:8080/drivedoctor/login");
        page.fill("input[name='email']", "agustinanichini277@gmail.com");
        page.fill("input[name='password']", "test");
        page.click("button[type='submit']");
        page.navigate("http://localhost:8080/drivedoctor/home");

        vistaHome = new VistaHome(page);
    }

    @AfterEach
    void cerrarContexto() {
        context.close();
    }



    @Test
    void deberiaEscribirAgustinComoNombreDeUsuario() {
        String nombreUsuario = vistaHome.obtenerTextoDeElemento("#nombreUsuario");

        assertThat("Hola, Agustin ",equalToIgnoringCase(nombreUsuario));
    }

    @Test
    void deberiaDirigirAVerMisVehiculosCuandoSeHaceClickEnMisVehiculos() {
        vistaHome.darClickEnUnElemento("#misVehiculos");
        String urlNavegada = this.vistaHome.obtenerURLActual();

        assertThat(urlNavegada,equalToIgnoringCase("http://localhost:8080/drivedoctor/verMisVehiculos"));
    }

    @Test
    void deberiaDirigirAAgregarVehiculoCuandoSeHaceClickEnAgregarVehiculo() {
        vistaHome.darClickEnUnElemento("#agregarVehiculo");
        String urlNavegada = this.vistaHome.obtenerURLActual();

        assertThat(urlNavegada,equalToIgnoringCase("http://localhost:8080/drivedoctor/nuevo-vehiculo"));
    }
}
