package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.infraestructura.ServicioLoginImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioLoginTest {


    private RepositorioUsuario repositorioUsuario;
    private ServicioLogin servicioLogin;

    @BeforeEach
    public void init(){
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioLogin = new ServicioLoginImpl(this.repositorioUsuario);
    }

  /*  @Test
    public void queNoPermitaRegistrarseSiLaPassTieneMenosDe6Caracteres() throws UsuarioExistente {
           Usuario user = mock(Usuario.class);
           user.setPassword("22");
           user.setEmail("prueba1@p.com");
           servicioLogin.registrar(user);
//Usuario userEncontrado = this.repositorioUsuario.buscarUsuario(user.getEmail(),user.getPassword());
        assertNull(userEncontrado );


    }*/

    @Test
    public void queSePuedaObtenerUnUsuarioAPartirDeEmailYPassword(){
        String email = "test@test.com";
        String password = "test";

        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("test");

        when(this.repositorioUsuario.buscarUsuario(email,password)).thenReturn(usuario);
        Usuario usuarioObtenido = this.servicioLogin.consultarUsuario(email,password);

        assertThat(usuarioObtenido.getEmail(), equalTo(email));
        assertThat(usuarioObtenido.getPassword(), equalTo(password));
        verify(this.repositorioUsuario, times(1)).buscarUsuario(email,password);
    }

    @Test
    public void queSePuedaRegistrarUnUsuarioNuevo() throws UsuarioExistente {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("test");

        when(this.repositorioUsuario.buscar("test@test.com")).thenReturn(null);
        this.servicioLogin.registrar(usuario);

        verify(this.repositorioUsuario,times(1)).buscar("test@test.com");
        verify(this.repositorioUsuario,times(1)).guardar(usuario);
    }

    @Test
    public void queSeLanceUnaExeptionCuandoSeQuieraRegistrarUnUsurioExistente() throws UsuarioExistente {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("test");

        when(this.repositorioUsuario.buscar("test@test.com")).thenReturn(usuario);

        assertThrows(UsuarioExistente.class,() -> this.servicioLogin.registrar(usuario));
    }
}
