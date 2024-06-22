package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.AnioInvalido;
import com.drivedoctor.dominio.excepcion.PatenteExistente;
import com.drivedoctor.dominio.excepcion.PatenteInvalida;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioVehiculoTest {
    private ServicioVehiculo servicioVehiculo;
    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioUsuario repositorioUsuario;
    private ServicioModelo servicioModelo;


    @BeforeEach
    public void init() {
        this.repositorioVehiculo = mock(RepositorioVehiculo.class);
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioVehiculo = new ServicioVehiculoImpl(this.repositorioVehiculo, this.repositorioUsuario);

    }


    @Test
    public void queAlCrearUnVehiculoSeAsocieAUnUsuarioExistente() throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente {
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

        Vehiculo vehiculo = new Vehiculo(marca, modelo, 2015, "AA203IK");

        // Mock del repositorio de usuarios
        when(repositorioUsuario.buscarPorId(usuarioId)).thenReturn(usuario);

        // Llamada al método del servicio
        servicioVehiculo.agregarVehiculo(usuarioId, vehiculo);

        // Verificación de que se haya guardado el vehículo
        verify(repositorioVehiculo).guardar(vehiculo);

        // Verificaciones de la asociación
        assertThat(usuario.getVehiculos().size(), equalTo(1));
        assertThat(usuario.getVehiculos(), hasItem(vehiculo));
        assertThat(vehiculo.getUsuario(), equalTo(usuario));
    }


}
