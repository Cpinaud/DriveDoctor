package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.*;
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
    public void queSePuedaObtenerElIdDeUnDiagnostico() throws ElementoNoEncontrado {
        Integer idDiagnostico = 1;
        Diagnostico diagnosticoMock = new Diagnostico();
        diagnosticoMock.setIdDiagnostico(idDiagnostico);

        when(this.repositorioDiagnostico.findById(1)).thenReturn(diagnosticoMock);
        Diagnostico diagnosticoEncontrado = this.servicioDiagnostico.findById(1);

        assertEquals(idDiagnostico, diagnosticoEncontrado.getIdDiagnostico());
    }

    @Test
    public void queEnCasoDeQueNoSeEncuentreElDiagnosticoDevuelvaElementoNoEncontradoException() throws ElementoNoEncontrado {

        when(this.repositorioDiagnostico.findById(anyInt())).thenReturn(null);


        assertThrows(ElementoNoEncontrado.class, () -> {
            this.servicioDiagnostico.findById(anyInt());
        });



        verify(repositorioDiagnostico, times(1)).findById(anyInt());
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoMotor(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("Motor");
        sintomasMock.add(new Sintoma(itemTableroMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 60.0;

        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoFrenos(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("Freno");
        sintomasMock.add(new Sintoma(itemTableroMock));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 20.0;

        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoFiltroGasolina(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("FiltroGasolina");
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
        when(itemTableroMock.getNombre()).thenReturn("Motor");
        ItemTablero itemTableroMock2 = mock(ItemTablero.class);

        when(itemTableroMock2.getNombre()).thenReturn("Freno");
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
        when(itemTableroFrenoMock.getNombre()).thenReturn("Freno");
        ItemTablero itemTableroGasolinaMock = mock(ItemTablero.class);
        when(itemTableroGasolinaMock.getNombre()).thenReturn("FiltroGasolina");
        ItemTablero itemTableroEpcMock = mock(ItemTablero.class);
        when(itemTableroEpcMock.getNombre()).thenReturn("EPC");

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
        when(itemTableroFrenoMock.getNombre()).thenReturn("Freno");
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
        when(itemTableroFrenoMock.getNombre()).thenReturn("Freno");
        ItemTablero itemTableroMotorMock = mock(ItemTablero.class);
        when(itemTableroMotorMock.getNombre()).thenReturn("Motor");
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
        when(itemTableroFrenoMock.getNombre()).thenReturn("Freno");
        ItemTablero itemTableroAirbagMock = mock(ItemTablero.class);
        when(itemTableroAirbagMock.getNombre()).thenReturn("Airbag");
        ItemTablero itemTableroMotorMock = mock(ItemTablero.class);
        when(itemTableroMotorMock.getNombre()).thenReturn("Motor");
        sintomasMock.add(new Sintoma(itemTableroAirbagMock));
        sintomasMock.add(new Sintoma(itemTableroFrenoMock));
        sintomasMock.add(new Sintoma(itemTableroMotorMock));


        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 100.0;

    //120 seria en caso de que se sume
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queAlNoRecibirNadaMeDevuelvaNull() throws AllItemsEqual, DemasiadosItems, DemasiadosSintomas {
        List<Integer> sintomaMock = new ArrayList<>();
        List<Diagnostico> diagnosticoEsperado = servicioDiagnostico.findDependingId(sintomaMock);
        assertNull(diagnosticoEsperado);

    }

    @Test
    public void queAlRecibirUnSintomaConUnIdMeDevuelvaSuDescripcionDeDiagnostico() throws AllItemsEqual, DemasiadosItems, DemasiadosSintomas {
        Integer idSintoma = 1;
        String descripcionDiagnostico = "Descripción de prueba";

        Diagnostico diagnostico1 = new Diagnostico();
        diagnostico1.setDescripcion(descripcionDiagnostico);
        diagnostico1.setIdDiagnostico(idSintoma);

        Sintoma sintoma1 = createSintoma(idSintoma, diagnostico1);

        List<Integer> sintomasMock = Arrays.asList(idSintoma);

        when(repositorioDiagnostico.obtenerPorSintomaId(idSintoma)).thenReturn(diagnostico1);
        List<Diagnostico> diagnosticos =servicioDiagnostico.findDependingId(sintomasMock);
        String descripcionObtenida = diagnosticos.get(0).getDescripcion();

        assertEquals(diagnosticos.size(), 1);
        assertEquals(descripcionDiagnostico, descripcionObtenida);
        verify(repositorioDiagnostico, times(1)).obtenerPorSintomaId(idSintoma);

    }


    @Test
    public void queAlRecibirDosElementosDelMismoElementoMeDevuelvaLaDescripcionDelItem() throws AllItemsEqual, DemasiadosItems, DemasiadosSintomas {
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
        when(repositorioSintoma.obtenerLosSintomasPorSusIds(anyList()))
                .thenReturn(Arrays.asList(sintoma1, sintoma2));

        assertThrows(AllItemsEqual.class, () -> {
            this.servicioDiagnostico.findDependingId(sintomasMock);
        });
    }

    @Test
    public void queAlRecibirDosElementosDeDiferenteItemMuestreElDiagnosticoDeCadaUno() throws AllItemsEqual, DemasiadosItems, DemasiadosSintomas {
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

        List<Diagnostico> diagnosticosObtenidos = servicioDiagnostico.findDependingId(sintomasMock);
        List<Diagnostico> diagnostocosEsperados = new ArrayList<>();
        diagnostocosEsperados.add(diagnostico1);
        diagnostocosEsperados.add(diagnostico2);

        assertEquals(diagnostocosEsperados, diagnosticosObtenidos);


    }

    @Test
    public void queAlRecibirTresElementosDelMismoItemLanzeLaExepcionAllItemsEquals() throws AllItemsEqual {
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;
        String descripcionEsperada = "prueba";

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        itemTableroMock.setDescripcion(descripcionEsperada);
        when(itemTableroMock.getDescripcion()).thenReturn(descripcionEsperada);

        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock);
        Sintoma sintoma3 = crearSintomaConItem(idSintoma3, itemTableroMock);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(Arrays.asList(idSintoma1, idSintoma2, idSintoma3)))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3));

        List<Integer> sintomasMock = Arrays.asList(idSintoma1, idSintoma2, idSintoma3);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(anyList()))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3));

        assertThrows(AllItemsEqual.class, () -> {
            this.servicioDiagnostico.findDependingId(sintomasMock);
        });
    }

    @Test
    public void queAlRecibirTresElementosDelMismoItemYUnoDiferenteNoMuestreSuDescripcion() throws AllItemsEqual, DemasiadosSintomas {
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
        List<Integer> sintomasId = new ArrayList<>();
        sintomasId.add(idSintoma1);
        sintomasId.add(idSintoma2);
        sintomasId.add(idSintoma3);
        sintomasId.add(idSintoma4);

        when(repositorioSintoma.obtenerLosSintomasPorSusIds(anyList()))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3, sintoma4));

        assertThrows(DemasiadosSintomas.class, () -> {
            this.servicioDiagnostico.findDependingId(sintomasId);
        });
    }

    @Test
    public void siRecibe3OMasSintomasDelMismoTipoMuestreElMensajeDeDescripcionDelItem() throws AllItemsEqual, DemasiadosItems, DemasiadosSintomas {
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
        when(repositorioSintoma.obtenerLosSintomasPorSusIds(anyList()))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3));

        assertThrows(AllItemsEqual.class, () -> {
            this.servicioDiagnostico.findDependingId(sintomasMock);
        });
    }

    @Test
    public void queAlRecibir3SintomasYNoSonDelMismoItemMuestreElMensajeDemasiadosItemsAcerqueseAUnTaller() throws AllItemsEqual {
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        ItemTablero itemTableroMock2 = mock(ItemTablero.class);
        ItemTablero itemTableroMock3 = mock(ItemTablero.class);

        Sintoma sintoma1 = crearSintomaConItem(idSintoma1, itemTableroMock);
        Sintoma sintoma2 = crearSintomaConItem(idSintoma2, itemTableroMock2);
        Sintoma sintoma3 = crearSintomaConItem(idSintoma3, itemTableroMock3);


        when(repositorioSintoma.obtenerLosSintomasPorSusIds(anyList()))
                .thenReturn(Arrays.asList(sintoma1, sintoma2, sintoma3));

        List<Integer> sintomasMock =Arrays.asList(idSintoma1, idSintoma2, idSintoma3);

        assertThrows(DemasiadosItems.class, () -> {
            this.servicioDiagnostico.findDependingId(sintomasMock);
        });


    }






    private Sintoma createSintoma(Integer idSintoma, Diagnostico diagnostico) {
        ItemTablero itemTableroMotorMock = mock(ItemTablero.class);
        when(itemTableroMotorMock.getNombre()).thenReturn("Motor");
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
