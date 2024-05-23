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
import java.util.Optional;

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
    public void queSePuedaObtenerElDignosticoDeUnSintoma(){
        //preparacion
        Integer diagnosticoId = 1;
        Diagnostico diagnosticoEsperado = new Diagnostico();
        diagnosticoEsperado.setIdDiagnostico(diagnosticoId);

        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(diagnosticoEsperado);

        Model model = mock(Model.class);


        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, model);

        assertEquals("mostrarDiagnostico", vista);
        verify(model).addAttribute("diagnostico", diagnosticoEsperado);
        verify(servicioDiagnostico).findById(diagnosticoId);

    }

    @Test
    public void queEnCasoDeQueNoSeEncuntreLanzeUnError(){
        Integer diagnosticoId = 1;

        when(servicioDiagnostico.findById(diagnosticoId)).thenReturn(null);
        Model model = mock(Model.class);

        String vista = controladorDiagnostico.obtenerDiagnostico(diagnosticoId, model);

        assertEquals("error", vista);
        verify(model).addAttribute("mensaje", "No se encuuentra ningun diagnostico asociado a este sintoma");

        verify(servicioDiagnostico).findById(diagnosticoId);
    }

    @Test
    public void queSePuedaObtenerElDiagnosticoBasadoEnSintomas(){
        Sintoma sintoma1Mock = new Sintoma();
        sintoma1Mock.setIdSintoma(1);
        Sintoma sintoma2Mock = new Sintoma();
        sintoma2Mock.setIdSintoma(2);

        List<Sintoma> sintomas = Arrays.asList(sintoma1Mock, sintoma2Mock);
        List<Integer> idsSintomas = new ArrayList<>();


        for(Sintoma sintoma : sintomas){
                idsSintomas.add(sintoma.getIdSintoma());
        }



    }




}
