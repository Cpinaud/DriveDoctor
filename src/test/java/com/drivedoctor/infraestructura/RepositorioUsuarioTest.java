package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.hibernate.Session;
import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioUsuarioTest {
    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioUsuarioImpl repositorioUsuario;

    @BeforeEach
    public void init(){
        this.repositorioUsuario = new RepositorioUsuarioImpl(this.sessionFactory);
    }


    @Test
    @Transactional
    @Rollback
    public void queSePuedaAgregarUnUsuario() throws UsuarioExistente {
        // Preparación
        Usuario usuario = new Usuario();
        usuario.setPassword("test2");
        usuario.setEmail("test@test2.com");

        // Ejecución
        this.repositorioUsuario.guardar(usuario);

        // Verificación
        Usuario itemObtenido = (Usuario) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Usuario WHERE password = :password AND email = :email", Usuario.class)
                .setParameter("password", "test2")
                .setParameter("email", "test@test2.com")
                .uniqueResult();

        assertThat(itemObtenido, notNullValue());
        assertThat(itemObtenido.getPassword(), equalTo("test2"));
        assertThat(itemObtenido.getEmail(), equalTo("test@test2.com"));
    }




    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnUsuarioPorEmail() throws UsuarioExistente {
        // Preparación
        Usuario usuario = new Usuario();
        usuario.setPassword("test2");
        usuario.setEmail("test@test2.com");
        String emailBuscado = "test@test2.com";

        this.repositorioUsuario.guardar(usuario);
        Usuario obtenido = this.repositorioUsuario.buscar(emailBuscado);

        assertThat(obtenido, notNullValue());
        assertThat(obtenido, equalTo(usuario));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaModificarUnUsuario() throws UsuarioExistente {
        // Preparación
        Usuario usuario = new Usuario();
        usuario.setPassword("test2");
        usuario.setEmail("test@test2.com");

        this.repositorioUsuario.guardar(usuario);

        usuario.setPassword("nuevaPassword");
        this.repositorioUsuario.modificar(usuario);

        Usuario usuarioModificado = (Usuario) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                .setParameter("email", "test@test2.com")
                .uniqueResult();

        assertThat(usuarioModificado, notNullValue());
        assertThat(usuarioModificado.getPassword(), equalTo("nuevaPassword"));
    }

   /* @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        Integer Idbuscada = 1;
        Session session = sessionFactory.getCurrentSession();
        session.save(usuario);


        Usuario buscado = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Usuario WHERE id = :idBuscado", Usuario.class)
                .setParameter("idBuscado", Idbuscada)
                .uniqueResult();

        assertThat(buscado, notNullValue());
        assertThat(buscado.getId(), Matchers.equalTo(Idbuscada));
    }*/

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerLosVehiculosDeUnUsuario() throws UsuarioExistente {
        // Preparación: Crear y guardar un usuario
        Usuario usuario = new Usuario();
        usuario.setPassword("test");
        usuario.setEmail("test@example.com");
        repositorioUsuario.guardar(usuario);


        Vehiculo vehiculo1 = new Vehiculo();
        vehiculo1.setUsuario(usuario);
        Vehiculo vehiculo2 = new Vehiculo();
        vehiculo2.setUsuario(usuario);

        Session session = sessionFactory.getCurrentSession();
        session.save(vehiculo1);
        session.save(vehiculo2);
        List<Vehiculo> vehiculos = repositorioUsuario.getMisVehiculos(usuario);

             assertThat(vehiculos, notNullValue());
        assertThat(vehiculos.size(), equalTo(2));
        assertThat(vehiculos.get(0).getUsuario(), equalTo(usuario));
        assertThat(vehiculos.get(1).getUsuario(), equalTo(usuario));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanBuscarVehiculosPorMarca() throws UsuarioExistente {
        Usuario usuario = new Usuario();
        usuario.setPassword("test");
        usuario.setEmail("test@example.com");
        repositorioUsuario.guardar(usuario);

        Marca marcaToyota = new Marca();
        marcaToyota.setNombre("Toyota");
        Marca marcaFord = new Marca();
        marcaFord.setNombre("Ford");
        Session session = sessionFactory.getCurrentSession();
        session.save(marcaToyota);
        session.save(marcaFord);


        Vehiculo vehiculo1 = new Vehiculo();
        vehiculo1.setMarca(marcaToyota);
        vehiculo1.setUsuario(usuario);

        Vehiculo vehiculo2 = new Vehiculo();
        vehiculo2.setMarca(marcaToyota);
        vehiculo2.setUsuario(usuario);

        Vehiculo vehiculo3 = new Vehiculo();
        vehiculo3.setMarca(marcaFord);
        vehiculo3.setUsuario(usuario);

        session.save(vehiculo1);
        session.save(vehiculo2);
        session.save(vehiculo3);


        List<Vehiculo> vehiculosToyota = repositorioUsuario.buscarVhPorMarca(usuario, marcaToyota);
        List<Vehiculo> vehiculosFord = repositorioUsuario.buscarVhPorMarca(usuario, marcaFord);

        // Verificación: Verificar que se devuelven los vehículos correctos
        assertThat(vehiculosFord, notNullValue());
        assertThat(vehiculosFord.size(), equalTo(1));
        assertThat(vehiculosFord.get(0), equalTo(vehiculo3));

        assertThat(vehiculosToyota, notNullValue());
        assertThat(vehiculosToyota.size(), equalTo(2));
    }
}
