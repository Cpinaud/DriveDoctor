package com.drivedoctor.punta_a_punta;

import com.drivedoctor.punta_a_punta.vistas.VistaHome;
import com.drivedoctor.punta_a_punta.vistas.VistaNuevoVehiculo;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class VistaNuevoVehiculoE2E {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    VistaNuevoVehiculo vistaNuevoVehiculo;

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
        page.click("#agregarVehiculo");
        page.navigate("http://localhost:8080/drivedoctor/nuevo-vehiculo");
        vistaNuevoVehiculo = new VistaNuevoVehiculo(page);
    }

    @AfterEach
    void cerrarContexto() {
        context.close();
    }

    @Test
    void deberiaAgregarUnNuevoVehiculoCuandoElUsuarioLleneElFormulario() {
        vistaNuevoVehiculo.seleccionarOpcion("#marca", "2"); // Selecciona la opción con valor "2" (Ford)
        vistaNuevoVehiculo.seleccionarOpcion("#modeloId", "201");
        vistaNuevoVehiculo.rellenarInput("#anoFabricacion","2005");
        vistaNuevoVehiculo.rellenarInput("#patente","AA123BB");
        vistaNuevoVehiculo.darClickEnUnElemento("#nuevoVehiculo");
         String url= vistaNuevoVehiculo.obtenerURLActual();

        String nombreVehiculo = vistaNuevoVehiculo.obtenerTextoDeElemento("#nombreMiVehiculo");
        String modeloVehiculo = vistaNuevoVehiculo.obtenerTextoDeElemento("#modeloMiVehiculo");
        String anioVehiculo = vistaNuevoVehiculo.obtenerTextoDeElemento("#anioMiVehiculo");
        String patenteVehiculo = vistaNuevoVehiculo.obtenerTextoDeElemento("#patenteMiVehiculo");

        assertThat(url, equalToIgnoringCase("http://localhost:8080/drivedoctor/verMisVehiculos"));
        assertThat(nombreVehiculo, equalToIgnoringCase("Ford"));
        assertThat(modeloVehiculo, equalToIgnoringCase("Fiesta"));
        assertThat(anioVehiculo, equalToIgnoringCase("2005"));
        assertThat(patenteVehiculo, equalToIgnoringCase("AA123BB"));
    }


}
