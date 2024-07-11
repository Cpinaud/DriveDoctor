package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.hamcrest.text.IsEqualIgnoringCase;
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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;


public class ControladorVehiculoTest {

    private ControladorVehiculo controladorVehiculo;
    private ServicioVehiculo servicioVehiculo;
    private ServicioMarca servicioMarca;
    private ServicioModelo servicioModelo;

    @BeforeEach
    public void init(){
        this.servicioVehiculo = mock(ServicioVehiculo.class);
        this.servicioMarca = mock(ServicioMarca.class);
        this.servicioModelo = mock(ServicioModelo.class);
        //this.servicioUsuario = new servicioUsuarioImpl();
        this.controladorVehiculo = new ControladorVehiculo(this.servicioMarca,this.servicioVehiculo,this.servicioModelo);
    }

    @Test
    public void queDevuelvaLaVistaDeVehiculosCuandoSeAgregaUnVehiculo() throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, ElementoNoEncontrado {
        // Preparar los datos de prueba
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Vehiculo vehiculo = mockeoVehiculo();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        doNothing().when(servicioVehiculo).agregarVehiculo(userId, vehiculo);

        // Ejecutar el método agregarVehiculo
        ModelAndView modelAndView = this.controladorVehiculo.agregarVehiculo(vehiculo, request, vehiculo.getId(), redirectAttributes);

        // Verificar la vista
        assertThat(modelAndView.getViewName(), IsEqualIgnoringCase.equalToIgnoringCase("redirect:/verMisVehiculos"));
    }

    @Test
    public void errorEnAnioFabricacionAlCrearVehiculoDeberiaVolverAFormularioYMostrarError() throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, ElementoNoEncontrado {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Vehiculo vehiculo = mockeoVehiculo();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        doThrow(AnioInvalido.class).when(servicioVehiculo).agregarVehiculo(userId, vehiculo);

        ModelAndView modelAndView = controladorVehiculo.agregarVehiculo(vehiculo, request, userId, redirectAttributes);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/nuevo-vehiculo"));
        assertThat(redirectAttributes.getFlashAttributes().get("error").toString(), equalToIgnoringCase("El año del vehiculo debe ser mayor o igual a 2000"));
    }

    @Test
    public void errorEnPatenteAlCrearVehiculoDeberiaVolverAFormularioYMostrarError() throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, ElementoNoEncontrado {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Vehiculo vehiculo = mockeoVehiculo();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        doThrow(PatenteInvalida.class).when(servicioVehiculo).agregarVehiculo(userId, vehiculo);

        ModelAndView modelAndView = controladorVehiculo.agregarVehiculo(vehiculo, request, userId, redirectAttributes);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/nuevo-vehiculo"));
        assertThat(redirectAttributes.getFlashAttributes().get("error").toString(), equalToIgnoringCase("El formato de la patente es inválido"));
    }

    @Test
    public void patenteExistenteAlCrearVehiculoDebeVolverAFormularioYMostrarError() throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, ElementoNoEncontrado {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Vehiculo vehiculo = mockeoVehiculo();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        doThrow(PatenteExistente.class).when(servicioVehiculo).agregarVehiculo(userId, vehiculo);

        ModelAndView modelAndView = controladorVehiculo.agregarVehiculo(vehiculo, request, userId, redirectAttributes);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/nuevo-vehiculo"));
        assertThat(redirectAttributes.getFlashAttributes().get("error").toString(), equalToIgnoringCase("Ya se ingresó un vehiculo con esa patente"));
    }

    /*@Test
    public void patenteExistenteAlEditarVehiculoDebeVolverAFormularioYMostrarError() throws UsuarioInexistente, AnioInvalido,PatenteInvalida,PatenteExistente {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        Vehiculo vehiculo = mockeoVehiculo();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        doThrow(PatenteExistente.class).when(servicioVehiculo).editarVehiculo(userId, vehiculoId,atributo,valor);

        ModelAndView modelAndView = controladorVehiculo.editarVehiculo(vehiculo, request, userId, redirectAttributes,atributo, valor);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/nuevo-vehiculo"));
        assertThat(redirectAttributes.getFlashAttributes().get("error").toString(), equalToIgnoringCase("Ya se ingresó un vehiculo con esa patente"));
    }*/



    private Vehiculo mockeoVehiculo() throws ElementoNoEncontrado {
        Vehiculo vehiculo = mock(Vehiculo.class);
        Modelo modelo = mock(Modelo.class);
        Marca marca = mock(Marca.class);
        when(servicioModelo.findById(anyInt())).thenReturn(modelo);
        when(modelo.getMarca()).thenReturn(marca);
        when(vehiculo.getId()).thenReturn(1);
        when(vehiculo.getModelo()).thenReturn(modelo);
        when(vehiculo.getAnoFabricacion()).thenReturn(2000);
        when(vehiculo.getPatente()).thenReturn("AA111AA");
        return vehiculo;
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
