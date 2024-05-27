package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import com.drivedoctor.dominio.Sintoma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorDiagnosticoTest {

    private ControladorDiagnostico controladorDiagnostico;
    private ServicioDiagnostico servicioDiagnostico;
    private Diagnostico diagnosticoMock;
    private Sintoma sintomaMock;


    @BeforeEach
    public void init(){
        this.servicioDiagnostico = mock(ServicioDiagnostico.class);
        this.controladorDiagnostico = new ControladorDiagnostico(this.servicioDiagnostico);
        diagnosticoMock = mock(Diagnostico.class);
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
        //Sintomas
        Sintoma sintoma1 = new Sintoma();
        sintoma1.setIdSintoma(1);
        Sintoma sintoma2= new Sintoma();
        sintoma2.setIdSintoma(2);

        List<Integer> idsSintomas = List.of(sintoma1.getIdSintoma(), sintoma2.getIdSintoma());
        // Diagnostico
        Diagnostico diagnosticoEsperado1 = new Diagnostico();
        diagnosticoEsperado1.setIdDiagnostico(1);

        Diagnostico diagnosticoEsperado2 = new Diagnostico();
        diagnosticoEsperado2.setIdDiagnostico(2);

        List<Diagnostico> diagnosticosEsperados = List.of(diagnosticoEsperado1, diagnosticoEsperado2);

        when(servicioDiagnostico.findById(sintoma1.getIdSintoma())).thenReturn(diagnosticoEsperado1);
        when(servicioDiagnostico.findById(sintoma2.getIdSintoma())).thenReturn(diagnosticoEsperado2);

        // Mock del Model
        Model model = mock(Model.class);
        // Ejecución
        String vista = controladorDiagnostico.obtenerDiagnosticoPorSintomas(idsSintomas, model);

        // Verificación
        assertEquals("mostrarDiagnostico", vista, "La vista devuelta debe ser 'mostrarDiagnostico'");
        verify(model).addAttribute("diagnosticos", diagnosticosEsperados);
        verify(servicioDiagnostico).findById(sintoma1.getIdSintoma());
        verify(servicioDiagnostico).findById(sintoma2.getIdSintoma());

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
    @Test
    public void queMuestreMensajeConDescripcionDelItemComoResultadoCuandoDosOmasSintomasCompartenMismoItem(){

    }

    @Test
    public void queMuestreMensajeDeSugerenciaCuandoSeSeleccionanMasDeTresSintomas(){ //muchos sintomas

    }
    @Test
    public void queMuestreMensajeDeSugerenciaCuandoSeObtienenTresOmasDiagnosticosAsociadoATresOmasSintomas(){ //muchos diagnosticos

    }
}
