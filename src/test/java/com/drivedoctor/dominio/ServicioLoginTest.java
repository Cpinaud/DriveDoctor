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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}
