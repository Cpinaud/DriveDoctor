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
    private ItemTablero itemTableroMock;
    private ServicioItemTablero servicioItemTableroMock;
    private ServicioVehiculo servicioVehiculoMock;

    @BeforeEach
    public void init(){

        this.servicioSintoma = mock(ServicioSintoma.class);
        this.servicioItemTableroMock = mock(ServicioItemTablero.class);
        this.servicioVehiculoMock = mock(ServicioVehiculo.class);
        this.controladorSintoma =  new ControladorSintoma(this.servicioSintoma, this.servicioItemTableroMock,this.servicioVehiculoMock);
        sintomaMock = mock(Sintoma.class);
        itemTableroMock = mock(ItemTablero.class);

    }

    @Test
    public void queAlCompletarTodoElFormularioSeGuardeYMeLleveALaVistaSintoma(){

        ModelAndView modelAndView = controladorSintoma.crearSintoma(sintomaMock,itemTableroMock);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/sintoma"));

    }

    @Test
    public void queSePuedaObtenerTodosLosSintomasDependiendoQueItemElijo() throws Exception {
        Integer idItemTablero = 1; // Suponiendo que este es el ID del ítem que deseas probar
        ItemTablero item = mock(ItemTablero.class);
        when(servicioItemTableroMock.findById(idItemTablero)).thenReturn(item);
        when(servicioSintoma.problemaEnTablero(item)).thenReturn(Arrays.asList(sintomaMock));
        Integer idVehiculo=1;
        ModelAndView modelAndView = controladorSintoma.mostrarSintomaDependiendoItem(idItemTablero,idVehiculo);

        verify(servicioItemTableroMock).findById(idItemTablero);
        verify(servicioSintoma).problemaEnTablero(item);

        List<Sintoma> sintomas = (List<Sintoma>) modelAndView.getModel().get("sintomas");
        assertThat(sintomas, hasSize(1));
    }




}
