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

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

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
    public void queSePuedaAgregarUnVehiculo() {
        // Preparación
        Marca marca = this.crearMarca("Renault");
        Modelo modelo = this.crearModelo("Clio", marca);
        Vehiculo vehiculo = this.crearVehiculo(marca, modelo, 2013, "AAA123");

        // Ejecución
        this.repositorioVehiculo.guardar(vehiculo);

        // Verificación
        Vehiculo itemObtenido = (Vehiculo) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Vehiculo WHERE patente = :patente", Vehiculo.class)
                .setParameter("patente", "AAA123")
                .uniqueResult();


        assertThat(itemObtenido, equalTo(vehiculo));
        assertThat(itemObtenido, notNullValue());
        assertThat(itemObtenido.getMarca().getNombre(), equalTo("Renault"));
        assertThat(itemObtenido.getModelo().getNombre(), equalTo("Clio"));
        assertThat(itemObtenido.getAnoFabricacion(), equalTo(2013));
        assertThat(itemObtenido.getPatente(), equalTo("AAA123"));
    }



    @Test
    @Transactional
    @Rollback
    public void queTraigaSoloVehiculosDeMarcaRenaultCuandoObtengoVehiculosPorMarcaRenault(){
        // preparacion
        Marca marca = this.crearMarca("Renault");
        Marca marca1 = this.crearMarca("Ford");
        Modelo modelo = this.crearModelo("Clio",marca);
        Modelo modelo1 = this.crearModelo("Sandero",marca);
        Modelo modelo2 = this.crearModelo("Fiesta",marca1);
        Vehiculo vehiculoRenault = new Vehiculo(marca, modelo,2013,"AAA132");
        Vehiculo vehiculoRenault1 = new Vehiculo(marca, modelo1,2021,"AB123RR");
        Vehiculo vehiculoFord = new Vehiculo(marca1, modelo2,2023,"AF444GG");

        this.sessionFactory.getCurrentSession().save(vehiculoRenault);
        this.sessionFactory.getCurrentSession().save(vehiculoRenault1);
        this.sessionFactory.getCurrentSession().save(vehiculoFord);

        // ejecucion
         List<Vehiculo> vehiculosObtenidos = this.repositorioVehiculo.getPorMarca(marca);

        // verificacion
        assertThat(vehiculosObtenidos.size(), equalTo(2));
    }


    private Vehiculo crearVehiculo(Marca marca, Modelo modelo,int anoFabricacion,String patente) {
        Vehiculo vehiculo = new Vehiculo(marca, modelo,anoFabricacion,patente);
        this.sessionFactory.getCurrentSession().save(vehiculo);
        return vehiculo;
    }

    private Marca crearMarca(String nombre) {
        Marca marca = new Marca(nombre);
        this.sessionFactory.getCurrentSession().save(marca);
        return marca;
    }

    private Modelo crearModelo(String nombre,Marca marca) {
        Modelo modelo = new Modelo(nombre,marca);
        this.sessionFactory.getCurrentSession().save(modelo);
        return modelo;
    }

}
