package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.dominio.excepcion.SintomaExistente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private ServicioDiagnostico servicioDiagnosticoMock;

    @BeforeEach
    public void init() {

        this.servicioSintoma = mock(ServicioSintoma.class);
        this.servicioItemTableroMock = mock(ServicioItemTablero.class);
        this.servicioVehiculoMock = mock(ServicioVehiculo.class);
        this.servicioDiagnosticoMock = mock(ServicioDiagnostico.class);
        this.controladorSintoma = new ControladorSintoma(this.servicioSintoma, this.servicioItemTableroMock, this.servicioVehiculoMock, this.servicioDiagnosticoMock);
        sintomaMock = mock(Sintoma.class);
        itemTableroMock = mock(ItemTablero.class);

    }

    @Test
    public void queAlCompletarTodoElFormularioSeGuardeYMeLleveALaVistaSintoma() throws ItemNoEncontrado, SintomaExistente, ElementoNoEncontrado {
        // Suponiendo que este es el ID del ítem que deseas probar

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(servicioItemTableroMock.findById(anyInt())).thenReturn(itemTableroMock);
        ModelAndView modelAndView = controladorSintoma.crearSintoma(sintomaMock,request,anyInt(),anyInt());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/sintoma"));


     /* ModelAndView modelAndView = controladorSintoma.crearSintoma(sintomaMock);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/sintoma"));
*/
    }

    @Test
    public void queSePuedaObtenerTodosLosSintomasDependiendoQueItemElijo() throws Exception, ItemNoEncontrado {
        Integer idItemTablero = 1; // Suponiendo que este es el ID del ítem que deseas probar
        ItemTablero item = mock(ItemTablero.class);
        when(servicioItemTableroMock.findById(idItemTablero)).thenReturn(item);
        when(servicioSintoma.problemaEnTablero(item)).thenReturn(Arrays.asList(sintomaMock));
        Integer idVehiculo = 1;
        ModelAndView modelAndView = controladorSintoma.mostrarSintomaDependiendoItem(idItemTablero, idVehiculo);

        verify(servicioItemTableroMock).findById(idItemTablero);
        verify(servicioSintoma).problemaEnTablero(item);

        List<Sintoma> sintomas = (List<Sintoma>) modelAndView.getModel().get("sintomas");
        assertThat(sintomas, hasSize(1));
    }

    @Test
    public void queAlGuardarSeGuardeConUnItemDeTableroAsignadoYConUnDiagnostico() throws ItemNoEncontrado, SintomaExistente {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Sintoma sintoma = new Sintoma();
        sintoma.setNombre("Auto con temblor");
        sintoma.setDescripcion("patina el embrague");

        ItemTablero itemTablero = new ItemTablero();
        itemTablero.setIdItemTablero(1);

        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(1);

        when(servicioItemTableroMock.findById(1)).thenReturn(itemTablero);
        when(servicioDiagnosticoMock.findById(1)).thenReturn(diagnostico);


        controladorSintoma.crearSintoma(sintoma, request, 1, 1);
        verify(servicioItemTableroMock).findById(1);
        verify(servicioDiagnosticoMock).findById(1);
        verify(servicioSintoma).guardarSintoma(argThat(s ->
                s.getItemTablero().equals(itemTablero) && s.getDiagnostico().equals(diagnostico)
        ));
    }

}

