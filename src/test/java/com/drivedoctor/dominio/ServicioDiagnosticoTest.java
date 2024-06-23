package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import com.drivedoctor.infraestructura.ServicioDiagnosticoImpl;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class ServicioDiagnosticoTest {

    private ServicioDiagnostico servicioDiagnostico;
    private RepositorioDiagnostico repositorioDiagnostico;
    private RepositorioSintoma repositorioSintoma;

    @BeforeEach
    public void init(){
        this.repositorioDiagnostico = mock(RepositorioDiagnostico.class);
        this.repositorioSintoma = mock(RepositorioSintoma.class);
        this.servicioDiagnostico = new ServicioDiagnosticoImpl(this.repositorioDiagnostico, this.repositorioSintoma);


    }

    @Test
    public void queSePuedaObtenerElIdDeUnDiagnostico(){
        Integer idDiagnostico = 1;
        Diagnostico diagnosticoMock = new Diagnostico();
        diagnosticoMock.setIdDiagnostico(idDiagnostico);

        when(this.repositorioDiagnostico.findById(1)).thenReturn(diagnosticoMock);
        Diagnostico diagnosticoEncontrado = this.servicioDiagnostico.findById(1);

        assertEquals(idDiagnostico, diagnosticoEncontrado.getIdDiagnostico());
    }

    @Test
    public void queEnCasoDeQueNoSeEncuentreDevuelvaNull() {
        Integer idDiagnostico = 1;
        when(repositorioDiagnostico.findById(idDiagnostico)).thenReturn(null);

        try {
            servicioDiagnostico.findById(idDiagnostico);
            fail("Se esperaba DiagnosticoNotFoundException");
        } catch (DiagnosticoNotFoundException e) {
            assertEquals("Diagnóstico no encontrado para el ID: " + idDiagnostico, e.getMessage());
        }


        verify(repositorioDiagnostico, times(1)).findById(idDiagnostico);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoMotor(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("ItemMotor");
        sintomasMock.add(new Sintoma(itemTableroMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 60.0;

        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoFrenos(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("ItemFreno");
        sintomasMock.add(new Sintoma(itemTableroMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 20.0;

        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoFiltroGasolina(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("ItemFiltroGasolina");
        sintomasMock.add(new Sintoma(itemTableroMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 40.0;

        assertEquals(riesgoEsperado, riesgoCalculado);

    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoNoTieneNingunItemEnElTablero(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(null));
        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 0.0;
        assertEquals(riesgoEsperado, riesgoCalculado);

    }

    @Test
    public void queSePuedaTenerMasDeUnItemEnElTablero(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("ItemMotor");
        ItemTablero itemTableroMock2 = mock(ItemTablero.class);

        when(itemTableroMock2.getNombre()).thenReturn("ItemFreno");
        sintomasMock.add(new Sintoma(itemTableroMock));
        sintomasMock.add(new Sintoma(itemTableroMock2));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 80.0;
        //Motor 60 + frenos 20
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaTenerMasDeDosItemEnElTablero(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroFrenoMock = mock(ItemTablero.class);
        when(itemTableroFrenoMock.getNombre()).thenReturn("ItemFreno");
        ItemTablero itemTableroGasolinaMock = mock(ItemTablero.class);
        when(itemTableroGasolinaMock.getNombre()).thenReturn("ItemFiltroGasolina");
        ItemTablero itemTableroEpcMock = mock(ItemTablero.class);
        when(itemTableroEpcMock.getNombre()).thenReturn("ItemEPC");

        sintomasMock.add(new Sintoma(itemTableroEpcMock));
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));
        sintomasMock.add(new Sintoma(itemTableroGasolinaMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 80.0;

        //Filtro Gasolina 40 + frenos 20 + EPC 20
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queEnCasoDeQueSeIngresenDosSintomasConElMismoItemNoSeSumen(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroFrenoMock = mock(ItemTablero.class);
        when(itemTableroFrenoMock.getNombre()).thenReturn("ItemFreno");
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 20.0;

        // frenos 20
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queEnCasoDeQueSeIngresenDosSintomasConElMismoItemNoSeSumenYAgregandoOtroItemAsiVerificamosQueSigaSumandoSiEsDiferente(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroFrenoMock = mock(ItemTablero.class);
        when(itemTableroFrenoMock.getNombre()).thenReturn("ItemFreno");
        ItemTablero itemTableroMotorMock = mock(ItemTablero.class);
        when(itemTableroMotorMock.getNombre()).thenReturn("ItemMotor");
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));
        sintomasMock.add(new Sintoma(itemTableroMotorMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 80.0;


        assertEquals(riesgoEsperado, riesgoCalculado);
    }
    @Test
    public void queEnCasoDeQueHallaVariosItemsYSupereEl100PorcientMuestreComoMaximoEseValor(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroFrenoMock = mock(ItemTablero.class);
        when(itemTableroFrenoMock.getNombre()).thenReturn("ItemFreno");
        ItemTablero itemTableroAirbagMock = mock(ItemTablero.class);
        when(itemTableroAirbagMock.getNombre()).thenReturn("ItemAirbag");
        ItemTablero itemTableroMotorMock = mock(ItemTablero.class);
        when(itemTableroMotorMock.getNombre()).thenReturn("ItemMotor");
        sintomasMock.add(new Sintoma(itemTableroAirbagMock));
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));
        sintomasMock.add(new Sintoma(itemTableroMotorMock));


        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 100.0;

    //120 seria en caso de que se sume
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queAlNoRecibirNadaMeDevuelvaNull(){
        List<Integer> sintomaMock = new ArrayList<>();
        String diagnosticoEsperado = servicioDiagnostico.findDependingId(sintomaMock);
        assertNull(diagnosticoEsperado);

    }

    @Test
    public void queAlRecibirUnSintomaConUnIdMeDevuelvaSuDescripcionDeDiagnostico() {
        Integer idSintoma = 1;
        String descripcionDiagnostico = "Descripción de prueba";

        Diagnostico diagnostico1 = new Diagnostico();
        diagnostico1.setDescripcion(descripcionDiagnostico);
        diagnostico1.setIdDiagnostico(idSintoma);

        Sintoma sintoma1 = createSintoma(idSintoma, diagnostico1);

        List<Integer> sintomasMock = Arrays.asList(idSintoma);

        when(repositorioDiagnostico.obtenerPorSintomaId(idSintoma)).thenReturn(diagnostico1);

        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);

        assertEquals(descripcionDiagnostico, descripcionObtenida);
        verify(repositorioDiagnostico, times(1)).obtenerPorSintomaId(idSintoma);
    }


    @Test
    public void queAlRecibirDosElementosDelMismoElementoMeDevuelvaLaDescripcionDelItem() {
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        String descripcionEsperada = "prueba";

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getDescripcion()).thenReturn(descripcionEsperada);

        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma1, idSintoma2)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2));

        List<Integer> sintomasMock = Arrays.asList(idSintoma1, idSintoma2);
        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);

        assertEquals(descripcionEsperada, descripcionObtenida);
    }

    @Test
    public void queAlRecibirDosElementosDeDiferenteItemMuestreElDiagnosticoDeCadaUno() {
        Integer idSintoma = 1;
        Integer idSintoma2 = 2;

        String descripcionDiagnostico = "Probando diag";
        String descripcionDiagnostico2 = "Probando diag2";

        Diagnostico diagnostico1 = new Diagnostico();
        diagnostico1.setDescripcion(descripcionDiagnostico);
        Diagnostico diagnostico2 = new Diagnostico();
        diagnostico2.setDescripcion(descripcionDiagnostico2);

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getIdItemTablero()).thenReturn(1);

        ItemTablero itemTableroMock2 = mock(ItemTablero.class);
        when(itemTableroMock.getIdItemTablero()).thenReturn(2);

        Sintoma sintoma1 = crearSintomaConItemYDiagnostico(idSintoma, itemTableroMock, diagnostico1);
        Sintoma sintoma2 = crearSintomaConItemYDiagnostico(idSintoma2, itemTableroMock2, diagnostico2);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma, idSintoma2)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2));

        List<Integer> sintomasMock = Arrays.asList(idSintoma, idSintoma2);

        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);
        String descripcionEsperada = descripcionDiagnostico + " " + descripcionDiagnostico2;

        assertEquals(descripcionEsperada, descripcionObtenida);


    }

    @Test
    public void queAlRecibirTresElementosDelMismoItemMuestreSuDescripcion()
    {
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;
        String descripcionEsperada = "prueba";

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getDescripcion()).thenReturn(descripcionEsperada);

        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock);
        Sintoma sintoma3 = crearSintomaConItem(idSintoma3, itemTableroMock);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma1, idSintoma2, idSintoma3)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3));

        List<Integer> sintomasMock = Arrays.asList(idSintoma1, idSintoma2, idSintoma3);
        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);

        assertEquals(descripcionEsperada, descripcionObtenida);
    }

    @Test
    public void queAlRecibirTresElementosDelMismoItemYUnoDiferenteNoMuestreSuDescripcion()
    {
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;
        Integer idSintoma4 = 4;
        String descripcionEsperada = "prueba";
        String noDescripcionEsperada = "pruebbba";

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getDescripcion()).thenReturn(descripcionEsperada);
        ItemTablero itemTableroMock2 = mock(ItemTablero.class);
        when(itemTableroMock2.getDescripcion()).thenReturn(noDescripcionEsperada);

        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock);
        Sintoma sintoma3 = crearSintomaConItem(idSintoma3, itemTableroMock);
        Sintoma sintoma4 = crearSintomaConItem(idSintoma4, itemTableroMock2);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma1, idSintoma2, idSintoma3, idSintoma4)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3, sintoma4));

        List<Integer> sintomasMock = Arrays.asList(idSintoma1, idSintoma2, idSintoma3, idSintoma4);
        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);

        assertNotEquals(descripcionEsperada, descripcionObtenida);
    }

    @Test
    public void siRecibe3OMasSintomasDelMismoTipoMuestreElMensajeDeDescripcionDelItem(){
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;
        Integer idSintoma4 = 4;
        String descripcionEsperada = "prueba";


        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getDescripcion()).thenReturn(descripcionEsperada);


        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock);
        Sintoma sintoma3 = crearSintomaConItem(idSintoma3, itemTableroMock);
        Sintoma sintoma4 = crearSintomaConItem(idSintoma4, itemTableroMock);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma1, idSintoma2, idSintoma3, idSintoma4)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3, sintoma4));

        List<Integer> sintomasMock = Arrays.asList(idSintoma1, idSintoma2, idSintoma3, idSintoma4);
        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);

        assertEquals(descripcionEsperada, descripcionObtenida);
    }

    @Test
    public void queAlRecibir3SintomasYNoSonDelMismoItemMuestreElMensajeDemasiadosSintomasAcerqueseAUnTaller(){
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;
        Integer idSintoma4 = 4;
        String descripcionEsperada = "prueba";
        String noDescripcionEsperada = "prueba";
        String mensajeEsperado = "Demasiados sintomas acerquese a un taller";

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getDescripcion()).thenReturn(descripcionEsperada);
        ItemTablero itemTableroMock2 = mock(ItemTablero.class);
        when(itemTableroMock2.getDescripcion()).thenReturn(noDescripcionEsperada);

        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock);
        Sintoma sintoma3 = crearSintomaConItem(idSintoma3, itemTableroMock);
        Sintoma sintoma4 = crearSintomaConItem(idSintoma4, itemTableroMock2);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma1, idSintoma2, idSintoma3, idSintoma4)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3, sintoma4));

        List<Integer> sintomasMock = Arrays.asList(idSintoma1, idSintoma2, idSintoma3, idSintoma4);
        String descripcionObtenida = servicioDiagnostico.findDependingId(sintomasMock);

        assertNotEquals(descripcionEsperada, descripcionObtenida);
        assertEquals(mensajeEsperado, descripcionObtenida);

    }



    


    private Sintoma createSintoma(Integer idSintoma, Diagnostico diagnostico) {
        ItemTablero itemTableroMotorMock = mock(ItemTablero.class);
        when(itemTableroMotorMock.getNombre()).thenReturn("ItemMotor");
        when(itemTableroMotorMock.getDescripcion()).thenReturn("prueba");
        Sintoma sintoma = new Sintoma(itemTableroMotorMock);
        sintoma.setDiagnostico(diagnostico);

        return sintoma;
    }

    private Sintoma crearSintomaConItem(Integer idSintoma, ItemTablero itemTablero) {
        Sintoma sintoma = new Sintoma(itemTablero);
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(idSintoma);
        sintoma.setDiagnostico(diagnostico);
        return sintoma;


    }

    private Sintoma crearSintomaConItemYDiagnostico(Integer idSintoma, ItemTablero itemTablero, Diagnostico diagnostico) {
        Sintoma sintoma = new Sintoma(itemTablero);
        diagnostico.setIdDiagnostico(idSintoma);
        sintoma.setDiagnostico(diagnostico);
        return sintoma;


    }

}
