package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.UserSinVhByMarca;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import com.drivedoctor.infraestructura.ServicioUsuarioImpl;
import com.drivedoctor.infraestructura.ServicioVehiculoImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioUsuarioTest {

    private ServicioUsuario servicioUsuario;
    private RepositorioUsuario repositorioUsuario;

    @BeforeEach
    public void init(){
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioUsuario = new ServicioUsuarioImpl(this.repositorioUsuario);
    }

    @Test
    public void queSePuedanObtenerTodosLosVehiculosDeUnUsuarioCuandoElUsuarioTengaVehiculos() throws UsuarioSinVehiculos, UsuarioInexistente {
        Usuario usuario = mock(Usuario.class);
        usuario.setId(1);
        Integer usuarioId = usuario.getId();
        when(repositorioUsuario.buscarPorId(usuarioId)).thenReturn(usuario);
        Marca marca = mock(Marca.class);
        Modelo modelo = mock(Modelo.class);

        List<Vehiculo> vehiculosMock = new ArrayList<>();
        vehiculosMock.add(new Vehiculo(marca, modelo,2015, "AA203IK"));
        vehiculosMock.add(new Vehiculo(marca,modelo,2020, "AA111OK"));
        vehiculosMock.add(new Vehiculo(marca,modelo,2018, "AA201OO"));
        when(this.repositorioUsuario.getMisVehiculos(usuario)).thenReturn(vehiculosMock);

        List<Vehiculo> vehiculosObtenidos= servicioUsuario.getMisVehiculos(usuario);

        assertThat(vehiculosObtenidos.size(), equalTo(3));
    }

    @Test
    public void queLanzeExcepcionSiElUserNoPoseeVehiculosDeLaMarcaBuscada() throws UserSinVhByMarca {
        Usuario usuario = mock(Usuario.class);
        usuario.setId(1);
        Integer usuarioId = usuario.getId();
        when(repositorioUsuario.buscarPorId(usuarioId)).thenReturn(usuario);
        Marca marca = new Marca("Renault");
        when(this.repositorioUsuario.buscarVhPorMarca(usuario,marca)).thenReturn(new ArrayList<>());

        assertThrows(UserSinVhByMarca.class, () -> {
            servicioUsuario.getVhPorMarca(usuario,marca);
        });

    }

    @Test
    public void queDevuelvaVehiculosDeLaMarcaBuscadaSiElUserLosPosee(){
        Usuario usuario = mock(Usuario.class);
        usuario.setId(1);
        Integer usuarioId = usuario.getId();
        when(repositorioUsuario.buscarPorId(usuarioId)).thenReturn(usuario);
        Marca marca = new Marca("Renault");
        Modelo modelo = mock(Modelo.class);

        List<Vehiculo> vehiculosRenault = new ArrayList<>();
        vehiculosRenault.add(new Vehiculo(marca, modelo,2015, "AA203IK"));
        vehiculosRenault.add(new Vehiculo(marca,modelo,2020, "AA111OK"));
        vehiculosRenault.add(new Vehiculo(marca,modelo,2018, "AA201OO"));


        when(this.repositorioUsuario.buscarVhPorMarca(usuario,marca)).thenReturn(vehiculosRenault);

        assertThat(vehiculosRenault.size(),equalTo(3));
    }
}
