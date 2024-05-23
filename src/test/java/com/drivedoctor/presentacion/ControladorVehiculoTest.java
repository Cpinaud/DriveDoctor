package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;


public class ControladorVehiculoTest {

    private ControladorVehiculo controladorVehiculo;
    private ServicioVehiculo servicioVehiculo;

    @BeforeEach
    public void init(){
        this.servicioVehiculo = mock(ServicioVehiculo.class);
        //this.servicioUsuario = new servicioUsuarioImpl();
        this.controladorVehiculo = new ControladorVehiculo(this.servicioVehiculo);
    }

    /*@Test
    public void queInformeErrorSiSeNavegaAVerVehiculosSinSerAdministrador(){
        // preparacion
        Long usuarioId = 1L;
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

    private HttpServletRequest mockeoSessionUser() {
        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(123L);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(123L);
        return request;
    }

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
