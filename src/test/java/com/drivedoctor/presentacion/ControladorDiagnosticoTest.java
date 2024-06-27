package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.AllItemsEqual;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.dominio.excepcion.VehiculoInvalido;
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
    public void mostrarVistaCuandoSeObtengaUnDiagnosticoAsociadoAunSintoma() throws VehiculoInvalido {
        //preparacion
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Integer diagnosticoId = 1;
        Diagnostico diagnosticoEsperado = new Diagnostico();
        diagnosticoEsperado.setIdDiagnostico(diagnosticoId);

        // Configuración del mock del servicio
        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(diagnosticoEsperado);

        Model model = mock(Model.class);

        Integer idVehiculo = 1;
        Integer idsSintomas = 1;
        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, idVehiculo,idsSintomas,request,model);

        assertEquals("mostrarDiagnostico", vista);
        verify(model).addAttribute("diagnostico", diagnosticoEsperado);
        verify(servicioDiagnostico).findById(diagnosticoId);

    }

    @Test
    public void mostrarVistaCuandoSeObtenganDosDiagnosticosAsociadosAdosSintomas() throws AllItemsEqual, ItemNoEncontrado {
        int idSintoma1 = 1;
        int idSintoma2 = 2;
        Integer idVh=1;
        List<Integer> idsSintomas = List.of(idSintoma1, idSintoma2);
        String idsSintomasStr = "1,2";
        Diagnostico diagnostico1 = new Diagnostico();
        diagnostico1.setIdDiagnostico(1);
        Diagnostico diagnostico2 = new Diagnostico();
        diagnostico1.setIdDiagnostico(2);
        //String diagnosticosEsperados = "Diagnostico1, Diagnostico2";
        List<Diagnostico> diagnosticosEsperados = new ArrayList<>();
        diagnosticosEsperados.add(diagnostico2);
        diagnosticosEsperados.add(diagnostico1);
        when(servicioDiagnostico.findDependingId(idsSintomas)).thenReturn(diagnosticosEsperados);

        Model model = mock(Model.class);

        // Ejecución
        String vista = controladorDiagnostico.obtenerDiagnosticoPorSintomas(idsSintomasStr,idVh, model);


        // Verificación

        verify(model).addAttribute("diagnosticos", diagnosticosEsperados);
        verify(model).addAttribute("idsSintomas", idsSintomas);
        verify(servicioDiagnostico).findDependingId(idsSintomas);
    }

    @Test
    public void siNoSeObtieneUnDiagnosticoAsociadoAunSintomaLanzarUnError() throws VehiculoInvalido {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Integer diagnosticoId = 1;

        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(null);
        Model model = mock(Model.class);

        Integer idVehiculo = 1;
        Integer idsSintomas = 1;
        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, idVehiculo,idsSintomas,request,model);


        assertEquals("error", vista, "La vista devuelta debe ser 'error'");
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
