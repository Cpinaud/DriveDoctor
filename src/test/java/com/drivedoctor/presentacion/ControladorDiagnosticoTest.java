package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import com.drivedoctor.dominio.Sintoma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorDiagnosticoTest {

    private ControladorDiagnostico controladorDiagnostico;
    private ServicioDiagnostico servicioDiagnostico;
    private Sintoma sintomaMock;


    @BeforeEach
    public void init(){
        this.servicioDiagnostico = mock(ServicioDiagnostico.class);
        this.controladorDiagnostico = new ControladorDiagnostico(this.servicioDiagnostico);
        sintomaMock = mock(Sintoma.class);
    }

    @Test
    public void mostrarVistaCuandoSeObtengaUnDiagnosticoAsociadoAunSintoma(){
        //preparacion
        Integer diagnosticoId = 1;
        Diagnostico diagnosticoEsperado = new Diagnostico();
        diagnosticoEsperado.setIdDiagnostico(diagnosticoId);

        // Configuración del mock del servicio
        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(diagnosticoEsperado);

        Model model = mock(Model.class);


        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, model);

        assertEquals("mostrarDiagnostico", vista);
        verify(model).addAttribute("diagnostico", diagnosticoEsperado);
        verify(servicioDiagnostico).findById(diagnosticoId);

    }

    @Test
    public void mostrarVistaCuandoSeObtenganDosDiagnosticosAsociadosAdosSintomas(){ //sintomas con items diferentes
        // Mock de Sintomas
        Sintoma sintomaMock1 = mock(Sintoma.class);
        Sintoma sintomaMock2 = mock(Sintoma.class);

        int idSintoma1 = 1;
        int idSintoma2 = 2;

        when(sintomaMock1.getIdSintoma()).thenReturn(idSintoma1);
        when(sintomaMock2.getIdSintoma()).thenReturn(idSintoma2);

        List<Integer> idsSintomas = List.of(idSintoma1, idSintoma2);

        // Diagnostico
        Diagnostico diagnosticoEsperado1 = new Diagnostico();
        diagnosticoEsperado1.setIdDiagnostico(1);

        Diagnostico diagnosticoEsperado2 = new Diagnostico();
        diagnosticoEsperado2.setIdDiagnostico(2);

        List<Diagnostico> diagnosticosEsperados = List.of(diagnosticoEsperado1, diagnosticoEsperado2);

        when(servicioDiagnostico.findBySintomaId(idSintoma1)).thenReturn(diagnosticoEsperado1);
        when(servicioDiagnostico.findBySintomaId(idSintoma2)).thenReturn(diagnosticoEsperado2);

        Model model = mock(Model.class);
        // Ejecución
        String vista = controladorDiagnostico.obtenerDiagnosticoPorSintomas(idsSintomas, model);

        // Verificación
        assertEquals("mostrarDiagnostico", vista, "La vista devuelta debe ser 'mostrarDiagnostico'");
        verify(model).addAttribute("diagnosticos", diagnosticosEsperados);
        verify(servicioDiagnostico).findBySintomaId(idSintoma1);
        verify(servicioDiagnostico).findBySintomaId(idSintoma2);

    }

    @Test
    public void siNoSeObtieneUnDiagnosticoAsociadoAunSintomaLanzarUnError(){
        Integer diagnosticoId = 1;

        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(null);
        Model model = mock(Model.class);

        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, model);

        assertEquals("error", vista);
        verify(model).addAttribute("mensaje", "No se encuuentra ningun diagnostico asociado a este sintoma");

        verify(servicioDiagnostico).findById(diagnosticoId);
    }
/*    @Test
    public void queMuestreMensajeConDescripcionDelItemComoResultadoCuandoDosOmasSintomasCompartenMismoItem(){

        Sintoma sintomaMock1 = mock(Sintoma.class);
        Sintoma sintomaMock2 = mock(Sintoma.class);
        Sintoma sintomaMock3 = mock(Sintoma.class);
    }

    @Test
    public void queMuestreMensajeDeSugerenciaCuandoSeSeleccionanMasDeTresSintomas(){ //muchos sintomas

    }
    @Test
    public void queMuestreMensajeDeSugerenciaCuandoSeObtienenTresOmasDiagnosticosAsociadoATresOmasSintomas(){ //muchos diagnosticos

    }*/
}
