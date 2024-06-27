package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorDiagnosticoTest {

    private ControladorDiagnostico controladorDiagnostico;
    private ServicioDiagnostico servicioDiagnostico;
    private ServicioSintoma servicioSintoma;
    private Sintoma sintomaMock;


    @BeforeEach
    public void init(){
        this.servicioDiagnostico = mock(ServicioDiagnostico.class);
        this.servicioSintoma = mock(ServicioSintoma.class);
        this.controladorDiagnostico = new ControladorDiagnostico(this.servicioDiagnostico, this.servicioSintoma);
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
    public void mostrarVistaCuandoSeObtenganDosDiagnosticosAsociadosAdosSintomas() {
        int idSintoma1 = 1;
        int idSintoma2 = 2;

        List<Integer> idsSintomas = List.of(idSintoma1, idSintoma2);
        String idsSintomasStr = "1,2";


        String diagnosticosEsperados = "Diagnostico1, Diagnostico2";
        when(servicioDiagnostico.findDependingId(idsSintomas)).thenReturn(diagnosticosEsperados);

        Model model = mock(Model.class);

        // Ejecución
        String vista = controladorDiagnostico.obtenerDiagnosticoPorSintomas(idsSintomasStr, model);

        // Verificación

        verify(model).addAttribute("diagnosticos", diagnosticosEsperados);
        verify(model).addAttribute("idsSintomas", idsSintomas);
        verify(servicioDiagnostico).findDependingId(idsSintomas);
    }

    @Test
    public void siNoSeObtieneUnDiagnosticoAsociadoAunSintomaLanzarUnError(){
        Integer diagnosticoId = 1;

        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(null);
        Model model = mock(Model.class);

        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, model);

        assertEquals("error", vista, "La vista devuelta debe ser 'error'");
        verify(model).addAttribute("mensaje", "No se encuentra ningun diagnostico asociado a este id");

        verify(servicioDiagnostico).findById(diagnosticoId);
    }

    @Test
    public void mostrarMapaAlCargarLaPaginaSiLoMandoAlTaller(){
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



    }

}
