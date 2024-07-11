package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;


public class ControladorUsuarioTest {

    private ControladorUsuario controladorUsuario;
    private ServicioUsuario servicioUsuario;

    private ServicioMarca servicioMarca;
    @BeforeEach
    public void init(){
        this.servicioUsuario = mock(ServicioUsuario.class);
        this.servicioMarca = mock(ServicioMarca.class);
        this.controladorUsuario = new ControladorUsuario(this.servicioUsuario,this.servicioMarca);
    }
    @Test
    public void queIndiqueNoTenerVehiculosCuandoSeConsultenLosVehiculosDeUnUsuarioQueNoTieneNinguno() throws UsuarioSinVehiculos, ElementoNoEncontrado {
        // preparacion
        HttpServletRequest request = this.mockeoSessionUser();
        when(controladorUsuario.verMisVehiculos(request)).thenThrow(UsuarioSinVehiculos.class);

        // ejecucion
        ModelAndView modelAndView = controladorUsuario.verMisVehiculos(request);

        // validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("misVehiculos"));
        assertThat(modelAndView.getModel().get("mensaje").toString(), equalToIgnoringCase("El usuario no posee vehiculos aún"));
    }


    @Test
    public void queAlSolicitarLaPantallaDeMisVehiculosSeMuestreLaVistaMisVehiculos() throws ElementoNoEncontrado {
        // preparacion
        HttpServletRequest request = this.mockeoSessionUser();


        //ejecucion
        ModelAndView mav = this.controladorUsuario.verMisVehiculos(request);
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("misVehiculos"));
    }


    @Test
    public void queAlBuscarVehiculosRenaultDevuelvaVehiculosDeEsaMarcaSiElUserLosPosee() throws UserSinVhByMarca, ElementoNoEncontrado {
        HttpServletRequest request = this.mockeoSessionUser();
        Integer userId = (Integer) request.getSession().getAttribute("ID");
        Usuario usuario = new Usuario();
        usuario.setId(userId);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(vehiculo);
        Marca marca = new Marca("Renault");
        marca.setId(1);
        Integer marcaId = marca.getId();
        when(servicioUsuario.findById(userId)).thenReturn(usuario);
        when(servicioMarca.findById(marcaId)).thenReturn(marca);
        when(servicioUsuario.getVhPorMarca(usuario,marca)).thenReturn(vehiculosMock);
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        ModelAndView mav = this.controladorUsuario.verMisVhPorMarca(request,marcaId,redirectAttributes);

        //verificacion
        List<Vehiculo> vehiculosObtenidos =  (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(mav.getViewName(), equalToIgnoringCase("misVehiculos"));
        assertThat(vehiculosObtenidos.size(), equalTo(1));
    }

    @Test
    public void queAlBuscarVehiculosRenaultDevuelvaMensajeDeNoExistentesSiElUserNoLosPosee() throws UserSinVhByMarca, ElementoNoEncontrado {
        HttpServletRequest request = this.mockeoSessionUser();
        Integer userId = (Integer) request.getSession().getAttribute("ID");
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        Marca marca = new Marca("Renault");
        marca.setId(1);
        Integer marcaId = marca.getId();
        when(servicioUsuario.findById(userId)).thenReturn(usuario);
        when(servicioMarca.findById(marcaId)).thenReturn(marca);

        when(servicioUsuario.getVhPorMarca(any(Usuario.class),any(Marca.class))).thenThrow(UserSinVhByMarca.class);

        ModelAndView mav = this.controladorUsuario.verMisVhPorMarca(request,marcaId,redirectAttributes);

        //verificacion

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/verMisVehiculos"));
        assertThat(mav.getModel().get("mensaje").toString(), equalToIgnoringCase("El usuario no posee vehiculos de la marca solicitada"));
    }



    private HttpServletRequest mockeoSessionUser() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(123);
        return request;
    }

}
