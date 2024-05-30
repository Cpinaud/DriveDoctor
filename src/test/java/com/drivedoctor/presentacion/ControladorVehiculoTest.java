package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


public class ControladorVehiculoTest {

    private ControladorVehiculo controladorVehiculo;
    private ServicioVehiculo servicioVehiculo;
    private ServicioMarca servicioMarca;

    @BeforeEach
    public void init(){
        this.servicioVehiculo = mock(ServicioVehiculo.class);
        this.servicioMarca = mock(ServicioMarca.class);
        //this.servicioUsuario = new servicioUsuarioImpl();
        this.controladorVehiculo = new ControladorVehiculo(this.servicioVehiculo,this.servicioMarca);
    }
    @Test
    public void queDevuelvaLaVistaDeVehiculosCuandoSeAgregaUnVehiculo() {
        // Preparar los datos de prueba

        Vehiculo vehiculo = mock(Vehiculo.class);

        HttpServletRequest request = this.mockeoSessionUser();
        when(request.getSession().getAttribute("ID")).thenReturn(1);

        // Ejecutar el método agregarVehiculo
        ModelAndView modelAndView = this.controladorVehiculo.agregarVehiculo(vehiculo, request);
        assertThat(modelAndView.getViewName(), IsEqualIgnoringCase.equalToIgnoringCase("redirect:/verMisVehiculos"));


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
    /*@Test
    public void queInformeErrorSiSeNavegaAVerVehiculosSinSerAdministrador(){
        // preparacion
        Integer usuarioId = 1;
        Usuario usuario = mock(Usuario.class);
        usuario.setRol("USER");
        usuario.setEmail("test@Test.com");
        HttpServletRequest request = this.mockeoSessionUser();
        when(servicioVehiculo.verVehiculos()).thenThrow(UserSinPermiso.class);

        ModelAndView modelAndView = controladorVehiculo.verVehiculos();

        // validacion
        assertThat(modelAndView.getViewName(), IsEqualIgnoringCase.equalToIgnoringCase("misVehiculos"));
        assertThat(modelAndView.getModel().get("error").toString(), IsEqualIgnoringCase.equalToIgnoringCase("No tiene permisos para ver esta información"));


    }
/*


/*
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
        vehiculosMock.add(new Vehiculo(Marca.RENAULT,Modelo.CLIO,2013,"AFG455"));
        when(this.servicioVehiculo.getPorMarca(Marca.RENAULT)).thenReturn(vehiculosMock);

        // ejecucion
        ModelAndView mav = this.controladorVehiculo.buscarPorMarca(Marca.RENAULT);

        // verificacion
        List<Vehiculo> items = (List<Vehiculo>) mav.getModel().get("vehiculos");
        assertThat(items.get(0).getMarca(), equalTo(Marca.RENAULT));
    }

*/

}
