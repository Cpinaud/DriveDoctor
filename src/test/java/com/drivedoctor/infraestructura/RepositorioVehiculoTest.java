package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.Modelo;
import com.drivedoctor.dominio.RepositorioVehiculo;
import com.drivedoctor.dominio.Vehiculo;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.drivedoctor.integracion.config.HibernateTestConfig;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class RepositorioVehiculoTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioVehiculo repositorioVehiculo;

    @BeforeEach
    public void init(){
        this.repositorioVehiculo = new RepositorioVehiculoImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaAgregarUnVehiculo(){
        // preparacion
        Vehiculo vehiculo = this.crearVehiculo(Marca.RENAULT, Modelo.CLIO);

        // ejecucion
        this.repositorioVehiculo.guardar(vehiculo);

        // verificacion
        Vehiculo itemObtenido = (Vehiculo) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Vehiculo where id = 1")
                .getSingleResult();

        assertThat(itemObtenido, equalTo(vehiculo));
    }

    @Test
    @Transactional
    @Rollback
    public void queTraigaSoloVehiculosDeMarcaRenaultCuandoObtengoVehiculosPorMarcaRenault(){
        // preparacion
        Vehiculo vehiculoRenault = new Vehiculo(Marca.RENAULT, Modelo.CLIO);
        Vehiculo vehiculoRenault1 = new Vehiculo(Marca.RENAULT, Modelo.SANDERO);
        Vehiculo vehiculoFord = new Vehiculo(Marca.FORD, Modelo.FIESTA);

        this.sessionFactory.getCurrentSession().save(vehiculoRenault);
        this.sessionFactory.getCurrentSession().save(vehiculoRenault1);
        this.sessionFactory.getCurrentSession().save(vehiculoFord);

        // ejecucion
         List<Vehiculo> vehiculosObtenidos = this.repositorioVehiculo.getPorMarca(Marca.RENAULT);

        // verificacion
        assertThat(vehiculosObtenidos.size(), equalTo(2));
    }


    private Vehiculo crearVehiculo(Marca marca, Modelo modelo) {
        Vehiculo vehiculo = new Vehiculo(marca, modelo);
        this.sessionFactory.getCurrentSession().save(vehiculo);
        return vehiculo;
    }

}
