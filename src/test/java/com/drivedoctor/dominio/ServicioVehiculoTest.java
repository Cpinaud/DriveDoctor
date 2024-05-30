package com.drivedoctor.dominio;


import com.drivedoctor.dominio.excepcion.UserSinPermiso;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import com.drivedoctor.infraestructura.ServicioVehiculoImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import com.drivedoctor.presentacion.ControladorVehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioVehiculoTest {
    private ServicioVehiculo servicioVehiculo;
    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioUsuario repositorioUsuario;

    @BeforeEach
    public void init(){
        this.repositorioVehiculo = mock(RepositorioVehiculo.class);
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioVehiculo = new ServicioVehiculoImpl(this.repositorioVehiculo,this.repositorioUsuario);
    }






    @Test
    public void queAlBuscarVehiculosPorMarcaRenaultDevuelvaLosVehiculosDeEsaMarca(){
        // preparacion
        Marca marca = mock(Marca.class);
        marca.setId(2);
        marca.setNombre("Ford");
        Modelo modelo = mock(Modelo.class);
        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo(marca, modelo,2015,"AA203IK"));
        when(this.repositorioVehiculo.getPorMarca(marca)).thenReturn(vehiculosMock);

        // ejecucion
        List<Vehiculo> items = this.servicioVehiculo.getPorMarca(marca);

        // verificacion
        assertThat(items.size(), equalTo(1)); // Existan 1 elementos
    }

    @Test
    public void queAlCrearUnVehiculoSeAsocieAUnUsuarioExistente() throws UsuarioInexistente {
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setEmail("test@Test.com");
        Marca marca = mock(Marca.class);
        marca.setId(2);
        marca.setNombre("Ford");
        Modelo modelo = mock(Modelo.class);

        Vehiculo vehiculo = new Vehiculo(marca,modelo,2015,"AA203IK");

        when(repositorioUsuario.buscarPorId(usuarioId)).thenReturn(usuario);
        servicioVehiculo.agregarVehiculo(usuarioId, vehiculo);

        verify(repositorioVehiculo).guardar(vehiculo);


        assertThat(usuario.getVehiculos().size(), equalTo(1));
        assertThat(usuario.getVehiculos(), hasItem(vehiculo));
        assertThat(vehiculo.getUsuario(), equalTo(usuario));
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
