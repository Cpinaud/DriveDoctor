package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;


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
    public void queIndiqueNoTenerVehiculosCuandoSeConsultenLosVehiculosDeUnUsuarioQueNoTieneNinguno() throws UsuarioSinVehiculos{
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
    public void queAlSolicitarLaPantallaDeMisVehiculosSeMuestreLaVistaMisVehiculos(){
        // preparacion
        HttpServletRequest request = this.mockeoSessionUser();


        //ejecucion
        ModelAndView mav = this.controladorUsuario.verMisVehiculos(request);
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("misVehiculos"));
    }

    private HttpServletRequest mockeoSessionUser() {
        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(123);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(123);
        return request;
    }


/*
    @Test
    public void queAlBuscarVehiculosDeMarcaRenaultDevuelvaLosVehiculosDeEsaMarca() {

        //preparacion

        String marca = "Renault";

        //ejecucion
        ModelAndView mav = this.controladorUsuario.verVehiculosPorMarca(marca);
        List<Vehiculo> vehiculosPorMarca = (List<Vehiculo>) mav.getModel().get("vehiculos");

        //verificacion
        assertThat(vehiculosPorMarca.get(0).getMarca(), is(equalTo(marca)));
        assertThat(vehiculosPorMarca.get(1).getMarca(), is(equalTo(marca)));
        assertThat(vehiculosPorMarca, instanceOf(List.class));
        assertThat(vehiculosPorMarca.size(), equalTo(2));

    }

    @Test
    public void queAlBuscarVehiculosDeMarcaRenaultYModeloClioDevuelvaLosVehiculosDeEsaMarcaYModelo() {

        //preparacion

        String marca = "Renault";
        String modelo = "Clio";

        //ejecucion
        ModelAndView mav = this.controladorUsuario.verVehiculoPorMarcaYModelo(marca,modelo);
        List<Vehiculo> vehiculoPorMarcaYModelo = (List<Vehiculo>) mav.getModel().get("vehiculos");

        //verificacion
        assertThat(vehiculoPorMarcaYModelo.get(0).getMarca(), is(equalTo(marca)));
        assertThat(vehiculoPorMarcaYModelo.get(0).getModelo(), is(equalTo(modelo)));

    }*/



}
