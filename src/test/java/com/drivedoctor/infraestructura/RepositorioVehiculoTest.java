package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.Modelo;
import com.drivedoctor.dominio.RepositorioVehiculo;
import com.drivedoctor.dominio.Vehiculo;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
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
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
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
        Vehiculo vehiculo = this.crearVehiculo(Marca.RENAULT, Modelo.CLIO,2013,"AAA123");

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
        Vehiculo vehiculoRenault = new Vehiculo(Marca.RENAULT, Modelo.CLIO,2013,"AAA132");
        Vehiculo vehiculoRenault1 = new Vehiculo(Marca.RENAULT, Modelo.SANDERO,2021,"AB123RR");
        Vehiculo vehiculoFord = new Vehiculo(Marca.FORD, Modelo.FIESTA,2023,"AF444GG");

        this.sessionFactory.getCurrentSession().save(vehiculoRenault);
        this.sessionFactory.getCurrentSession().save(vehiculoRenault1);
        this.sessionFactory.getCurrentSession().save(vehiculoFord);

        // ejecucion
         List<Vehiculo> vehiculosObtenidos = this.repositorioVehiculo.getPorMarca(Marca.RENAULT);

        // verificacion
        assertThat(vehiculosObtenidos.size(), equalTo(2));
    }


    private Vehiculo crearVehiculo(Marca marca, Modelo modelo,int anoFabricacion,String patente) {
        Vehiculo vehiculo = new Vehiculo(marca, modelo,anoFabricacion,patente);
        this.sessionFactory.getCurrentSession().save(vehiculo);
        return vehiculo;
    }

}
