package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorDiagnosticoTest {

    private ControladorDiagnostico controladorDiagnostico;
    private ServicioDiagnostico servicioDiagnostico;
    private ServicioVehiculo servicioVehiculo;
    private ServicioSintoma servicioSintoma;

    private ServicioItemTablero servicioItemTablero;
    private Sintoma sintomaMock;


        @BeforeEach
    public void init(){
        this.servicioDiagnostico = mock(ServicioDiagnostico.class);
        this.servicioSintoma = mock(ServicioSintoma.class);
        this.servicioItemTablero = mock(ServicioItemTablero.class);
        this.controladorDiagnostico = new ControladorDiagnostico(this.servicioDiagnostico, this.servicioSintoma,this.servicioItemTablero);
        sintomaMock = mock(Sintoma.class);
    }

    @Test
    public void mostrarVistaCuandoSeObtengaUnDiagnosticoAsociadoAunSintoma() throws  ElementoNoEncontrado {
        //preparacion
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Integer diagnosticoId = 1;
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(diagnosticoId);
       List<Diagnostico> diagnosticoEsperado = new ArrayList<>();
        diagnosticoEsperado.add(diagnostico);

        // Configuración del mock del servicio
        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(diagnostico);

        Model model = mock(Model.class);

        Integer idVehiculo = 1;
        Integer idsSintomas = 1;
        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, idVehiculo,idsSintomas,request,model);

        assertEquals("mostrarDiagnostico", vista);
        verify(model).addAttribute("diagnostico", diagnosticoEsperado);
        verify(servicioDiagnostico).findById(diagnosticoId);

    }

    @Test
    public void mostrarVistaCuandoSeObtenganDosDiagnosticosAsociadosAdosSintomas() throws AllItemsEqual, ElementoNoEncontrado, DemasiadosItems, DemasiadosSintomas, ItemNoEncontrado {

        Integer idVh=1;
        String idsSintomasStr = "1,2";
        Diagnostico diagnostico1 = new Diagnostico();
        diagnostico1.setIdDiagnostico(1);
        Diagnostico diagnostico2 = new Diagnostico();
        diagnostico2.setIdDiagnostico(2);
        List<Diagnostico> diagnosticosEsperados = new ArrayList<>();
        diagnosticosEsperados.add(diagnostico1);
        diagnosticosEsperados.add(diagnostico2);
        when(servicioDiagnostico.findDependingId(anyList())).thenReturn(diagnosticosEsperados);

        Model model = mock(Model.class);

        // Ejecución
        String vista = controladorDiagnostico.obtenerDiagnosticoPorSintomas(idsSintomasStr,idVh, model);


        // Verificación
        assertEquals("diagnosticos", vista);
        verify(model).addAttribute("diagnosticos", diagnosticosEsperados);


    }

    @Test
    public void siNoSeObtieneUnDiagnosticoAsociadoAunSintomaLanzarElementoNoEncontrado() throws ElementoNoEncontrado {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Integer diagnosticoId = 1;
        doThrow(ElementoNoEncontrado.class).when(servicioDiagnostico).findById(diagnosticoId);

        Model model = mock(Model.class);

        Integer idVehiculo = 1;
        Integer idsSintomas = 1;
        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, idVehiculo,idsSintomas,request,model);



        verify(model).addAttribute("mensaje", "No se encuentra ningun diagnostico asociado a este id");

        verify(servicioDiagnostico).findById(diagnosticoId);
    }

   /*REVISARRRRRRR!!!!! @Test


    public void mostrarMapaAlCargarLaPaginaSiLoMandoAlTaller() throws AllItemsEqual {
        Integer idSintoma1 = 1;
        Integer idSintoma2 = 2;
        Integer idSintoma3 = 3;

        List<Integer> idsSintomas = List.of(idSintoma1, idSintoma2, idSintoma3);
        String idsSintomasStr = "1,2,3";

        String diagnosticoEsperado = "Demasiados sintomas acequese a un taller";
        when(servicioDiagnostico.findDependingId(idsSintomas)).thenReturn(diagnosticoEsperado);

        Model model = mock(Model.class);

        String vista = controladorDiagnostico.obtenerDiagnosticoPorSintomas(idsSintomasStr, model);
        verify(model).addAttribute("diagnosticoConMapa", diagnosticoEsperado);
        assertEquals("diagnosticoConMapa", vista);



    }*/

}
