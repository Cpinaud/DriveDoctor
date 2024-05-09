package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.servlet.ModelAndView;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorUsuarioTest {

    private ControladorUsuario controladorUsuario;
    private ServicioUsuario servicioUsuario;

    @BeforeEach
    public void init(){
        this.servicioUsuario = mock(ServicioUsuario.class);
        //this.servicioUsuario = new servicioUsuarioImpl();
        this.controladorUsuario = new ControladorUsuario(this.servicioUsuario);
    }

   /* @Test
    public void queAlSolicitarLaPantallaDeMisVehiculosSeMuestreLaVistaMisVehiculos(){
        //preparacion
        Usuario usuarioMock = mock(Usuario.class);
        //ejecucion
        ModelAndView mav = this.controladorUsuario.verMisVehiculos(usuarioMock);
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("misVehiculos"));
    }
   /* @Test
    public void queSePuedaAgregarUnVehiculoAUnUsuario() {
        //preparacion

        Usuario usuario = new Usuario();
        usuario.setEmail("test@unlam.edu.ar");

        Vehiculo vehiculoMock = new Vehiculo("Renault","Sandero");
        this.controladorUsuario.agregarVehiculo(usuario, vehiculoMock);

        ModelAndView mav = this.controladorUsuario.verMisVehiculos(usuario);

        //verificacion
        List<Vehiculo> vehiculos =  (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(vehiculos.size(), equalTo(1));
    }

    /*@Test
    public void queAlMostrarLaPantallaDeMisVehiculosMeMuestreTodosMisVehiculos(){
        //preparacion
        Usuario usuarioMock = mock(Usuario.class);
        Vehiculo vehiculo1Mock = new Vehiculo();
        Vehiculo vehiculo2Mock = new Vehiculo();

        Usuario usuarioMock = new Usuario();
        when(this.servicioUsuario.verMisVehiculos(usuarioMock)).thenReturn(vehiculosMock);

        //ejecucion
        ModelAndView mav = this.controladorUsuario.verMisVehiculos(usuarioMock);

        //verificacion
        List<Vehiculo> vehiculos = (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(vehiculos, instanceOf(List.class));
        assertThat(vehiculos.size(), equalTo(3));


    }*/


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
