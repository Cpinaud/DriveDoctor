package com.drivedoctor.punta_a_punta;

import com.drivedoctor.punta_a_punta.vistas.VistaHome;
import com.drivedoctor.punta_a_punta.vistas.VistaVerMisVehiculos;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class VistaVerMisVehiculosE2E {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    VistaVerMisVehiculos vistaVerMisVehiculos;

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
        page.click("#misVehiculos");
        page.navigate("http://localhost:8080/drivedoctor/verMisVehiculos");

        vistaVerMisVehiculos = new VistaVerMisVehiculos(page);
    }

    @AfterEach
    void cerrarContexto() {
        context.close();
    }

    @Test
    void deberiaEscribirElNombreDeLaMarcaDelVehiculoDelUsuario(){
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#nombreMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("Renault"));
    }
    @Test
    void deberiaEscribirElNombreDelModeloDelVehiculoDelUsuario(){
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#modeloMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("Clio"));
    }
    @Test
    void deberiaEscribirElAñoDelVehiculoDelUsuario(){
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#anioMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("2015"));
    }

    @Test
    void deberiaEscribirLaPatenteDelVehiculoDelUsuario(){
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#patenteMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("AA395BB"));
    }

    @Test
    void deberiaEscribirUnMensajeAlBuscarUnaMarcaDeVehiculoQueElUsuarioNoPosee(){
        vistaVerMisVehiculos.seleccionarOpcion("#marca", "2");
        vistaVerMisVehiculos.darClickEnUnElemento("#buscar");
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#mensajeMiVehiculo");

        assertThat(textContent, equalToIgnoringCase("El usuario no posee vehiculos de la marca solicitada"));
    }

    @Test
    void alHacerClickEnModificarDeberiaEnviarnosARellenarElFomularioDeModificacionDeEseVehiculo(){
       vistaVerMisVehiculos.darClickEnUnElemento("#modificarMiVehiculo");
        String urlNavegada = this.vistaVerMisVehiculos.obtenerURLActual();

        assertThat(urlNavegada, equalToIgnoringCase("http://localhost:8080/drivedoctor/modificar-vehiculo/1"));
    }

    @Test
    void alHacerClickEnModificarPodamosCambiarElAñoDeEseVehiculo(){
       vistaVerMisVehiculos.darClickEnUnElemento("#modificarMiVehiculo");
       vistaVerMisVehiculos.rellenarInput("#anoFabricacion","2019");
       vistaVerMisVehiculos.darClickEnUnElemento("#vehiculoModificado");

        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#anioMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("2019"));

    }

    @Test
    void alHacerClickEnModificarPodamosCambiarLaPatenteDeEseVehiculo(){
       vistaVerMisVehiculos.darClickEnUnElemento("#modificarMiVehiculo");
       vistaVerMisVehiculos.rellenarInput("#patente","CC395DD");
       vistaVerMisVehiculos.darClickEnUnElemento("#vehiculoModificado");

        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#patenteMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("CC395DD"));
    }

    @Test
    void deberiaDirigirAVerMisVehiculosCuandoSeHaceClickEnCancelarDentroDeModificarVehiculo() {
        vistaVerMisVehiculos.darClickEnUnElemento("#modificarMiVehiculo");
        vistaVerMisVehiculos.darClickEnUnElemento("#cancelar");
        String urlNavegada = this.vistaVerMisVehiculos.obtenerURLActual();

        assertThat(urlNavegada,equalToIgnoringCase("http://localhost:8080/drivedoctor/verMisVehiculos"));
    }
    @Test
    void alHacerClickEnDiagnosticarNosDebeMostrarLaVistaDeSintomaConLaPatenteDelAuto(){
        vistaVerMisVehiculos.darClickEnUnElemento("#diagnosticar");
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#diagnosticarVehiculo");

        assertThat(textContent,equalToIgnoringCase("Diagnosticar unidad CC395DD"));
    }

    @Test
    void alHacerClickEnEleminarNosAparezcaElModalYLoEliminemosTocandoAceptar(){
        vistaVerMisVehiculos.darClickEnUnElemento("#eleminarVehiculo");
        vistaVerMisVehiculos.darClickEnUnElemento("#confirmDeleteButton");
        String textContent = vistaVerMisVehiculos.obtenerTextoDeElemento("#mensajeMiVehiculo");

        assertThat(textContent,equalToIgnoringCase("El usuario no posee vehiculos aún"));
    }

}
