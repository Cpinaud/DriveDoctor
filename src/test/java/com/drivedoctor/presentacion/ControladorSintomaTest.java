package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

public class ControladorSintomaTest {

    private ControladorSintoma controladorSintoma;
    private ServicioSintoma servicioSintoma;
    private Sintoma sintomaMock;

    @BeforeEach
    public void init(){

        this.servicioSintoma = mock(ServicioSintoma.class);
        this.controladorSintoma =  new ControladorSintoma(this.servicioSintoma);
        sintomaMock = mock(Sintoma.class);

    }

    @Test
    public void queAlCompletarTodoElFormularioSeGuardeYMeLleveALaVistaSintoma(){

        ModelAndView modelAndView = controladorSintoma.crearSintoma(sintomaMock);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/sintoma"));

    }

    @Test
    public void queSePuedaObtenerTodosLosSintomasDependiendoQueItemElijo() throws Exception{
        ItemTablero item = ItemTablero.ItemFiltroGasolina;
        when(servicioSintoma.problemaEnTablero(ItemTablero.ItemFiltroGasolina)).thenReturn(Arrays.asList(sintomaMock));
        ModelAndView modelAndView = controladorSintoma.mostrarSintomaDependiendoItem(item);

        verify(servicioSintoma).problemaEnTablero(ItemTablero.ItemFiltroGasolina);

        List<Sintoma> sintomas = (List<Sintoma>) modelAndView.getModel().get("sintomas");
        assertThat(sintomas, hasSize(1));
    }



}
