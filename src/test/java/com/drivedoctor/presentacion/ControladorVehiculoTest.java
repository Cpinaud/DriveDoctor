package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioVehiculo;
import com.drivedoctor.dominio.ServicioVehiculoImpl;
import com.drivedoctor.dominio.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class ControladorVehiculoTest {

    private ControladorVehiculo controladorVehiculo;
    private ServicioVehiculo servicioVehiculo;

    @BeforeEach
    public void init(){
        //this.servicioVehiculo = mock(ServicioVehiculo.class);
        this.servicioVehiculo = new ServicioVehiculoImpl();
        this.controladorVehiculo = new ControladorVehiculo(this.servicioVehiculo);
    }

    @Test
    public void queAlSolicitarLaPantallaDeVehiculosSeMuestreLaVistaVehiculos(){
        //preparacion

        //ejecucion
        ModelAndView mav = this.controladorVehiculo.verVehiculos();
        //verificacion
        assertThat(mav.getViewName(),equalToIgnoringCase("Vehiculos"));
    }

    @Test
    public void queAlIngresarALaPantallaDeVehiculosMeMuestreTodosLosVehiculos(){
        //preparacion


        //ejecucion
        ModelAndView mav = this.controladorVehiculo.verVehiculos();

        //verificacion
        List<Vehiculo> vehiculos = (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(vehiculos, instanceOf(List.class));
        assertThat(vehiculos.size(), equalTo(3));


    }

    @Test
    public void queAlBuscarVehiculosDeMarcaRenaultDevuelvaLosVehiculosDeEsaMarca() {

        //preparacion

        String marca = "Renault";

        //ejecucion
        ModelAndView mav = this.controladorVehiculo.verVehiculosPorMarca(marca);
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
        ModelAndView mav = this.controladorVehiculo.verVehiculoPorMarcaYModelo(marca,modelo);
        List<Vehiculo> vehiculoPorMarcaYModelo = (List<Vehiculo>) mav.getModel().get("vehiculos");

        //verificacion
        assertThat(vehiculoPorMarcaYModelo.get(0).getMarca(), is(equalTo(marca)));
        assertThat(vehiculoPorMarcaYModelo.get(0).getModelo(), is(equalTo(modelo)));

    }



}
