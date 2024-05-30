package com.drivedoctor.dominio;

import com.drivedoctor.infraestructura.ServicioDiagnosticoImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioDiagnosticoTest {

    private ServicioDiagnostico servicioDiagnostico;
    private RepositorioDiagnostico repositorioDiagnostico;

    @BeforeEach
    public void init(){
        this.repositorioDiagnostico = mock(RepositorioDiagnostico.class);
        this.servicioDiagnostico = new ServicioDiagnosticoImpl(this.repositorioDiagnostico);
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
    public void queEnCasoDeQueNoSeEncuentreDevuelvaNull(){
        Integer idDiagnostico = 1;

        when(this.repositorioDiagnostico.findById(idDiagnostico)).thenReturn(null);
        Diagnostico diagnosticoEncontrado = this.servicioDiagnostico.findById(1);

        assertNull(diagnosticoEncontrado );

    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoMotor(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemMotor));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 60.0;

        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoFrenos(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 20.0;

        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaSaberElRiesgoDelCocheCuandoUnAutoTieneUnItemDeTipoFiltroGasolina(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemFiltroGasolina));

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
        sintomasMock.add(new Sintoma(ItemTablero.ItemMotor));
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 80.0;
        //Motor 60 + frenos 20
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queSePuedaTenerMasDeDosItemEnElTablero(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemFiltroGasolina));
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));
        sintomasMock.add(new Sintoma(ItemTablero.ItemEPC));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 80.0;

        //Filtro Gasolina 40 + frenos 20 + EPC 20
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queEnCasoDeQueSeIngresenDosSintomasConElMismoItemNoSeSumen(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 20.0;

        // frenos 20
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

    @Test
    public void queEnCasoDeQueSeIngresenDosSintomasConElMismoItemNoSeSumenYAgregandoOtroItemAsiVerificamosQueSigaSumandoSiEsDiferente(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));
        sintomasMock.add(new Sintoma(ItemTablero.ItemMotor));

        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 80.0;


        assertEquals(riesgoEsperado, riesgoCalculado);
    }
    @Test
    public void queEnCasoDeQueHallaVariosItemsYSupereEl100PorcientMuestreComoMaximoEseValor(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.ItemAirbag));
        sintomasMock.add(new Sintoma(ItemTablero.ItemFreno));
        sintomasMock.add(new Sintoma(ItemTablero.ItemMotor));


        double riesgoCalculado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomasMock);
        double riesgoEsperado = 100.0;

    //120 seria en caso de que se sume
        assertEquals(riesgoEsperado, riesgoCalculado);
    }

}
