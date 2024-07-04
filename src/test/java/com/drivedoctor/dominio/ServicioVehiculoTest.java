package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.*;
import com.drivedoctor.infraestructura.ServicioVehiculoImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.implementation.ExceptionMethod.throwing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ServicioVehiculoTest {
    private ServicioVehiculo servicioVehiculo;
    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioDiagnostico repositorioDiagnostico;
    private RepositorioSintoma repositorioSintoma;
    private ServicioModelo servicioModelo;

    private HttpServletRequest request;
    private HttpSession session;




    @BeforeEach
    public void init() {
        this.repositorioVehiculo = mock(RepositorioVehiculo.class);
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.request = mock(HttpServletRequest.class);
        this.session = mock(HttpSession.class);
        this.repositorioSintoma = mock(RepositorioSintoma.class);
        this.repositorioDiagnostico = mock(RepositorioDiagnostico.class);
        this.servicioVehiculo = new ServicioVehiculoImpl(this.repositorioVehiculo, this.repositorioUsuario,this.repositorioSintoma,this.repositorioDiagnostico);

    }


    @Test
    public void queAlCrearUnVehiculoSeAsocieAUnUsuarioExistente() throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, ElementoNoEncontrado, UsuarioExistente {
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setEmail("test@Test.com");
        Marca marca = new Marca();
        marca.setId(2);
        marca.setNombre("Ford");
        Modelo modelo = new Modelo();
        modelo.setId(3);
        modelo.setNombre("Focus");
        List<Vehiculo> vehiculos= new ArrayList<>();


        Vehiculo vehiculo = new Vehiculo(marca, modelo, 2015, "AA203IK");
        vehiculos.add(vehiculo);
        // Mock del repositorio de usuarios
        when(repositorioUsuario.findById(usuarioId)).thenReturn(usuario);
        when(repositorioUsuario.getMisVehiculos(usuario)).thenReturn(vehiculos);

        // Llamada al método del servicio
        servicioVehiculo.agregarVehiculo(usuarioId, vehiculo);

        // Verificación de que se haya guardado el vehículo
        verify(repositorioVehiculo).guardar(vehiculo);
        List<Vehiculo> vehiculosObtenidos = repositorioUsuario.getMisVehiculos(usuario);
        // Verificaciones de la asociación
        assertThat(vehiculosObtenidos.size(), equalTo(1));
        assertThat(vehiculosObtenidos, hasItem(vehiculo));
        assertThat(vehiculo.getUsuario(), equalTo(usuario));
    }

    @Test
    public void queSePuedanVerLosVehiculosSiElUsuarioEsAdmin() throws UserSinPermiso {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ROL")).thenReturn("ADMIN");

        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo());
        vehiculos.add(new Vehiculo());
        vehiculos.add(new Vehiculo());
        when(repositorioVehiculo.getVehiculos()).thenReturn(vehiculos);

        List<Vehiculo> result = servicioVehiculo.verVehiculos(request);

        assertThat(result, equalTo(vehiculos));
        verify(repositorioVehiculo, times(1)).getVehiculos();
    }

    @Test
    public void queSeLanceLaExeptionSiElUsuarioNoTienePermiso() throws UserSinPermiso {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ROL")).thenReturn("NOadmin");

        assertThrows(UserSinPermiso.class, () -> {
            servicioVehiculo.verVehiculos(request);
        });

        // Verificar que el método getVehiculos no se llama
        verify(repositorioVehiculo, never()).getVehiculos();
    }

    @Test
    public void queSePuedanObtenerVehiculosPorMarca()  {
        //vehiculo 1
        Marca marca = new Marca();
        marca.setId(2);
        marca.setNombre("Ford");
        Modelo modelo = new Modelo();
        modelo.setId(3);
        modelo.setNombre("Focus");
        //vehiculo 2
        marca.setId(3);
        marca.setNombre("Fiat");
        Modelo modelo1 = new Modelo();
        modelo.setId(4);
        modelo.setNombre("Cronos");

        Vehiculo vehiculo = new Vehiculo(marca, modelo, 2015, "AA203IK");
        Vehiculo vehiculo1 = new Vehiculo(marca, modelo1, 2016, "AA300AA");

        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo);
        vehiculos.add(vehiculo1);

        when(repositorioVehiculo.getPorMarca(marca)).thenReturn(vehiculos);
        List<Vehiculo> result =  this.servicioVehiculo.getPorMarca(marca);

        assertThat(result, equalTo(vehiculos));
        verify(repositorioVehiculo, times(1)).getPorMarca(marca);
    }

    @Test
    public void queSePuedaObtenerUnVehiculoPorSuId() throws ElementoNoEncontrado {
        Integer idVehiculo = 1;
        Marca marca = new Marca();
        marca.setId(2);
        marca.setNombre("Ford");
        Modelo modelo = new Modelo();
        modelo.setId(3);
        modelo.setNombre("Focus");
        Vehiculo vehiculoEsperado = new Vehiculo(marca, modelo, 2015, "AA203IK");
        vehiculoEsperado.setId(idVehiculo);

        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(vehiculoEsperado);
        Vehiculo vehiculoResultado = servicioVehiculo.findById(idVehiculo);

        verify(repositorioVehiculo, times(1)).findById(idVehiculo);
        assertThat(vehiculoResultado, equalTo(vehiculoEsperado));
    }

    @Test
    public void queSeLanceLaExcepcionElementoNoEncontradoSiNoExisteUnVehiculoConElIdSolicitado() throws ElementoNoEncontrado {

        when(repositorioVehiculo.findById(anyInt())).thenThrow(ElementoNoEncontrado.class);

        assertThrows(ElementoNoEncontrado.class, () -> {
            servicioVehiculo.findById(1);
        });

        verify(repositorioVehiculo, times(1)).findById(1);
    }


    @Test
    public void queSePuedaObtenerVehiculosPorPatente() throws VehiculoInexistente {
        Integer idVehiculo = 1;
        Marca marca = new Marca();
        marca.setId(2);
        marca.setNombre("Ford");
        Modelo modelo = new Modelo();
        modelo.setId(3);
        modelo.setNombre("Focus");
        Vehiculo vehiculo = new Vehiculo(marca, modelo, 2015, "AA203IK");
        String patenteDeVehiculo = vehiculo.getPatente();
        String patenteEsperada = "AA203IK";

        when(repositorioVehiculo.getByPatente(patenteDeVehiculo)).thenReturn(vehiculo);
        this.servicioVehiculo.buscarByPatente(patenteDeVehiculo);

        verify(repositorioVehiculo, times(1)).getByPatente(patenteEsperada);
        assertThat(vehiculo, equalTo(vehiculo));
    }

    @Test
    public void queSeLanceLaExcepcionSiNoExisteUnVehiculoConEsaPatente() {
        String patenteNoValida = "BB222CC";
        when(repositorioVehiculo.getByPatente(patenteNoValida)).thenReturn(null);

        assertThrows(VehiculoInexistente.class, () -> {
            servicioVehiculo.buscarByPatente(patenteNoValida);
        });

        verify(repositorioVehiculo, times(1)).getByPatente(patenteNoValida);
    }

    @Test
    public void queSeValideUnVehiculoConRespectoAUnUsuario() throws ElementoNoEncontrado {
        Integer idVehiculo = 1;
        Integer idVehiculoPorPatente = 1;
        Integer userId = 1;

        Vehiculo vehiculoMock = mock(Vehiculo.class);
        Usuario usuarioMock = mock(Usuario.class);
        when(usuarioMock.getId()).thenReturn(userId);
        when(vehiculoMock.getUsuario()).thenReturn(usuarioMock);
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(vehiculoMock);

        assertDoesNotThrow(() -> {
            servicioVehiculo.validarVehiculoUser(idVehiculo, idVehiculoPorPatente, userId);
        });

        verify(repositorioVehiculo, times(1)).findById(idVehiculo);
    }

    @Test
    public void queLanceVehiculoInvalidoCuandoElIdDelUsuarioNoCoincide() throws ElementoNoEncontrado {
        Integer idVehiculo = 1;
        Integer idVehiculoPorPatente = 1;
        Integer userId = 1;

        Vehiculo vehiculoMock = mock(Vehiculo.class);
        Usuario usuarioMock = mock(Usuario.class);
        when(usuarioMock.getId()).thenReturn(2); // No coincide con userId
        when(vehiculoMock.getUsuario()).thenReturn(usuarioMock);
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(vehiculoMock);

        assertThrows(VehiculoInvalido.class, () -> {
            servicioVehiculo.validarVehiculoUser(idVehiculo, idVehiculoPorPatente, userId);
        });

        verify(repositorioVehiculo, times(1)).findById(idVehiculo);
    }

    @Test
    public void queLanceVehiculoInvalidoCuandoIdVehiculoNoCoincide() {
        Integer idVehiculo = 1;
        Integer idVehiculoPorPatente = 2; // No coincide con idVehiculo
        Integer userId = 1;

        assertThrows(VehiculoInvalido.class, () -> {
            servicioVehiculo.validarVehiculoUser(idVehiculo, idVehiculoPorPatente, userId);
        });
    }

    @Test
    public void queSePuedaEliminarVehiculo() {
        Vehiculo vehiculo = new Vehiculo();

        servicioVehiculo.eliminarVehiculo(vehiculo);

        verify(repositorioVehiculo, times(1)).eliminar(vehiculo);
    }


    @Test
    public void queAlModificarVehiculoDeberiaLanzarElementoNoEncontradoSiElUsuarioNoExiste() throws ElementoNoEncontrado {
        Integer usuarioId = 1;
        when(this.repositorioUsuario.findById(usuarioId)).thenThrow(ElementoNoEncontrado.class);

        assertThrows(ElementoNoEncontrado.class, () -> {
            servicioVehiculo.modificarVehiculo(usuarioId, 2, "AAA111", 2015);
        });
    }

    @Test
    public void queAlModificarVehiucloDeberiaLanzarElementoNoEncontradoSiElVehiculoNoExiste() throws ElementoNoEncontrado {
        Integer usuarioId = 1;
        Integer idVehiculo = 2;
        when(repositorioUsuario.findById(usuarioId)).thenReturn(new Usuario());

        when(this.repositorioVehiculo.findById(idVehiculo)).thenThrow(ElementoNoEncontrado.class);
        assertThrows(ElementoNoEncontrado.class, () -> {
            servicioVehiculo.modificarVehiculo(usuarioId, idVehiculo, "AAA111", 2015);
        });
    }

    @Test
    public void queAlModificarVehiculoDeberiaLanzarAnioInvalidoSiElMismoNoCumpleLaValidacionEstablecida() throws ElementoNoEncontrado,AnioInvalido {
        Integer usuarioId = 1;
        Integer idVehiculo = 2;
        when(repositorioUsuario.findById(usuarioId)).thenReturn(new Usuario());
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(new Vehiculo());

        assertThrows(AnioInvalido.class, () -> {
            servicioVehiculo.modificarVehiculo(usuarioId, idVehiculo, "AAA111", 1999);
        });
    }

    @Test
    public void queAlModificarVehiculoDeberiaLanzarPatenteInvalidaSiNoCumpleConLaValidacionEstablecida() throws PatenteInvalida, ElementoNoEncontrado {
        Integer usuarioId = 1;
        Integer idVehiculo = 2;
        when(repositorioUsuario.findById(usuarioId)).thenReturn(new Usuario());
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(new Vehiculo());

        assertThrows(PatenteInvalida.class, () -> {
            servicioVehiculo.modificarVehiculo(usuarioId, idVehiculo, "INVALIDA", 2015);
        });
    }

    @Test
    public void queAlModificarVehiculoDeberiaLanzarPatenteExistenteSiYaExisteUnVehiculoConEsaPatente() throws PatenteExistente, ElementoNoEncontrado {
        Integer usuarioId = 1;
        Integer idVehiculo = 2;
        Vehiculo vehiculoExistente = new Vehiculo();
        vehiculoExistente.setId(3);
        when(repositorioUsuario.findById(usuarioId)).thenReturn(new Usuario());
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(new Vehiculo());
        when(repositorioVehiculo.getByPatente("AAA111")).thenReturn(vehiculoExistente);

        assertThrows(PatenteExistente.class, () -> {
            servicioVehiculo.modificarVehiculo(usuarioId, idVehiculo, "AAA111", 2015);
        });
    }

    @Test
    public void deberiaLanzarVehiculoSinCambios() throws ElementoNoEncontrado {
        Integer usuarioId = 1;
        Integer idVehiculo = 2;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setAnoFabricacion(2015);
        vehiculo.setPatente("AAA111");
        vehiculo.setId(idVehiculo);
        vehiculo.setUsuario(usuario);

        when(repositorioUsuario.findById(usuarioId)).thenReturn(usuario);
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(vehiculo);
        when(repositorioVehiculo.getByPatente("AAA111")).thenReturn(vehiculo);

        assertThrows(vehiculoSinCambios.class, () -> {
            servicioVehiculo.modificarVehiculo(usuarioId, idVehiculo, "AAA111", 2015);
        });

        verify(repositorioVehiculo, never()).modificar(vehiculo);
    }

    @Test
    public void deberiaModificarVehiculoExitosamente() throws UsuarioInexistente,
            AnioInvalido, PatenteInvalida,
            PatenteExistente, VehiculoInexistente,
            vehiculoSinCambios, ElementoNoEncontrado {
        Integer usuarioId = 1;
        Integer idVehiculo = 2;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setAnoFabricacion(2010);
        vehiculo.setPatente("BBB222");

        when(repositorioUsuario.findById(usuarioId)).thenReturn(new Usuario());
        when(repositorioVehiculo.findById(idVehiculo)).thenReturn(vehiculo);
        when(repositorioVehiculo.getByPatente("AAA111")).thenReturn(null);

        servicioVehiculo.modificarVehiculo(usuarioId, idVehiculo, "AAA111", 2015);

        assertThat("AAA111", equalTo(vehiculo.getPatente()));
        assertThat(2015, equalTo(vehiculo.getAnoFabricacion()));
        verify(repositorioVehiculo, times(1)).modificar(vehiculo);
    }
}
