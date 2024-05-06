package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioUsuario;
import com.drivedoctor.dominio.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;


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

    @Test
    public void queAlSolicitarLaPantallaDeVehiculosSeMuestreLaVistaVehiculos(){
        //preparacion

        //ejecucion
        ModelAndView mav = this.controladorUsuario.verMisVehiculos();
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("Vehiculos"));
    }

    @Test
    public void queAlIngresarALaPantallaDeMisVehiculosMeMuestreTodosMisVehiculos(){
        //preparacion
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo("Renault", "Clio"));
        vehiculosMock.add(new Vehiculo("Ford", "Fiesta"));
        vehiculosMock.add(new Vehiculo("Renault", "Sandero"));

        when(this.servicioUsuario.verMisVehiculos()).thenReturn(vehiculosMock);

        //ejecucion
        ModelAndView mav = this.controladorUsuario.verMisVehiculos();

        //verificacion
        List<Vehiculo> vehiculos = (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(vehiculos, instanceOf(List.class));
        assertThat(vehiculos.size(), equalTo(3));


    }

    @Test
    public void queAlSolicitarAgregarUnVehiculoSeAgregueALaPantallaDeAgregarVehiculo(){
        //preparacion
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo("Renault", "Clio"));
        vehiculosMock.add(new Vehiculo("Ford", "Fiesta"));
        vehiculosMock.add(new Vehiculo("Renault", "Sandero"));

        when(this.servicioUsuario.verMisVehiculos()).thenReturn(vehiculosMock);

        Vehiculo nuevoVehiculo = new Vehiculo("Renault","Fluence");


        //ejecución
        vehiculosMock.add(nuevoVehiculo);
        ModelAndView mav = this.controladorUsuario.verMisVehiculos();
        //verificacion
        List<Vehiculo> vehiculos = (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(vehiculos, instanceOf(List.class));
        assertThat(vehiculos.size(), equalTo(4));

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
