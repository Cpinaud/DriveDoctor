package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorVehiculoTest {

    private ControladorVehiculo controladorVehiculo;
    private ServicioVehiculo servicioVehiculo;

    @BeforeEach
    public void init(){
        this.servicioVehiculo = mock(ServicioVehiculo.class);
        //this.servicioUsuario = new servicioUsuarioImpl();
        this.controladorVehiculo = new ControladorVehiculo(this.servicioVehiculo);
    }


    @Test
    public void queAlSolicitarLaPantallaDeMisVehiculosSeMuestreLaVistaMisVehiculos(){
        //preparacion
        //ejecucion
        ModelAndView mav = this.controladorVehiculo.verVehiculos();
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("misVehiculos"));
    }

    @Test
    public void queAlSolicitarLaPantallaDeAgregarVehiculoSeMuestreLaVistaNuevoVehiculo(){
        //preparacion
        //ejecucion
        ModelAndView mav =this.controladorVehiculo.nuevoVehiculo();
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("nuevo-vehiculo"));
    }


    @Test
    public void queAlSolicitarLaPantallaDeVehiculosSeMuestrenTodosLosVehiculo(){
        //preparacion
        Vehiculo vehiculoMock = mock(Vehiculo.class);
        Vehiculo vehiculoMock1 = mock(Vehiculo.class);
        Vehiculo vehiculoMock2 = mock(Vehiculo.class);
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(vehiculoMock);
        vehiculosMock.add(vehiculoMock1);
        vehiculosMock.add(vehiculoMock2);
        when(this.servicioVehiculo.verVehiculos()).thenReturn(vehiculosMock);
        //ejecucion
        ModelAndView mav =this.controladorVehiculo.verVehiculos();
        //verificacion
        List<Vehiculo> vehiculos =  (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(vehiculos.size(), equalTo(3));
    }

    @Test
    public void queAlBuscarVehiculosDeMarcaRenaultTraigaVehiculosDeEsaMarca(){
        // preparacion
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo(Marca.RENAULT,Modelo.CLIO));
        when(this.servicioVehiculo.getPorMarca(Marca.RENAULT)).thenReturn(vehiculosMock);

        // ejecucion
        ModelAndView mav = this.controladorVehiculo.buscarPorMarca(Marca.RENAULT);

        // verificacion
        List<Vehiculo> items = (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(items.get(0).getMarca(), equalTo(Marca.RENAULT));
    }



}
