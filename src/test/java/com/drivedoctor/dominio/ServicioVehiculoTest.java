package com.drivedoctor.dominio;


import com.drivedoctor.infraestructura.ServicioVehiculoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioVehiculoTest {
    private ServicioVehiculo servicioVehiculo;
    private RepositorioVehiculo repositorioVehiculo;

    @BeforeEach
    public void init(){
        this.repositorioVehiculo = mock(RepositorioVehiculo.class);
        this.servicioVehiculo = new ServicioVehiculoImpl(this.repositorioVehiculo);
    }


    @Test
    public void queSePuedanObtenerTodosLosVehiculos(){
        // preparacion
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo(Marca.RENAULT, Modelo.CLIO));
        vehiculosMock.add(new Vehiculo(Marca.VOLKSWAGEN,Modelo.GOL));
        vehiculosMock.add(new Vehiculo(Marca.FORD,Modelo.FIESTA));
        when(this.repositorioVehiculo.getVehiculos()).thenReturn(vehiculosMock);

        // ejecucion
        List<Vehiculo> items = this.servicioVehiculo.verVehiculos();

        // verificacion
        assertThat(items.size(), equalTo(3)); // Existan 3 elementos
    }
    @Test
    public void queAlBuscarVehiculosPorMarcaRenaultDevuelvaLosVehiculosDeEsaMarca(){
        // preparacion
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo(Marca.RENAULT, Modelo.CLIO));
        when(this.repositorioVehiculo.getPorMarca(Marca.RENAULT)).thenReturn(vehiculosMock);

        // ejecucion
        List<Vehiculo> items = this.servicioVehiculo.getPorMarca(Marca.RENAULT);

        // verificacion
        assertThat(items.size(), equalTo(1)); // Existan 1 elementos
    }
    /* @Test
    public void queSePuedaAgregarUnVehiculoAlUsuario(){
        // Preparación
        Usuario usuario = new Usuario();
        usuario.setEmail("test@unlam.edu.ar");

        Vehiculo vehiculo = new Vehiculo("Fiat", "Palio");

        // Mock del repositorio de usuario

        // Mock del servicio de vehículo
        //ServicioVehiculo servicioVehiculo = mock(ServicioVehiculo.class);
        servicioUsuario.agregarVehiculo(usuario,vehiculo);

        assertThat(usuario.getVehiculos().size(), equalTo(1));
        assertThat(usuario.getVehiculos().get(0), equalTo(vehiculo));
    }
    /*
    @Test
    public void queSePuedanObtenerTodosLosVehiculosDeUnUsuario(){
        // preparacion
        Usuario usuario = new Usuario();
        usuario.setEmail("test@unlam.edu.ar");

        Vehiculo vehiculo = new Vehiculo("Fiat","Palio");
        this.servicioUsuario.agregarVehiculo(usuario,vehiculo);

        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(vehiculo);

        when(this.servicioUsuario.verMisVehiculos(usuario)).thenReturn(vehiculosMock);
        // ejecucion
        List<Vehiculo> vehiculos = this.servicioUsuario.verMisVehiculos(usuario);


        // verificacion
        assertThat(vehiculos.size(), equalTo(1)); // Existan 3 elementos
    }

*/
}
